package jdbc;

import model.Employee;
import model.EmployeeList;
import model.StockItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseModel {
    Connection connection;
    PreparedStatement departmentStatement;
    PreparedStatement employeeStatement;
    PreparedStatement employeeQuery;
    PreparedStatement stockItemStatement;
    private PropertyChangeSupport changeSupport;

    public DataBaseModel() {
        setConnection();
        departmentStatement = prepareDepartmentStatement();
        employeeStatement = prepareEmployeeStatement();
        stockItemStatement = prepareStockItemStatement();
        changeSupport = new PropertyChangeSupport(this);


    }

    //ServerSender Listens to DataBase Model
    public void addListener(String evt, PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(evt, listener);
    }

    //Conection to postgres
    public void setConnection() {
        //Settings for Database
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pw = "123321";
        connection = null;
        //magic. Something about loading the JDBC driver.
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creates a empty employee table
    public void createEmployeeTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Employee (" +
                "   EmployeeID varchar(6) NOT NULL PRIMARY KEY," +
                "departmentID varchar(30) NOT NULL ," +
                " firstName varchar(30) NOT NULL," +
                "lastName varchar(30) NOT NULL," +
                " FOREIGN KEY (departmentid) REFERENCES \"Sep2\".Department(departmentID)" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Creates a empty department table;
    public void createDepartmentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Department (" +
                "departmentID varchar(30) NOT NULL PRIMARY KEY ," +
                " departmentName varchar(30) NOT NULL" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    public void createSep2Schema() {
        String sql = "CREATE SCHEMA IF NOT EXISTS \"Sep2\";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creates empty StockItem table
    public void createStockItemTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".StockItem (" +
                "   id varchar(6) NOT NULL PRIMARY KEY," +
                "name varchar(30) NOT NULL ," +
                " quantity int NOT NULL," +
                "price int NOT NULL," +
                "expiryDate Date" +

                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Prepares a employee statement to improve DB performance for simple queries that are used multiple times
    public PreparedStatement prepareEmployeeStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".employee (employeeID,departmentID,firstName,lastName) " +
                "SELECT * FROM (SELECT ?,?,?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT employeeID FROM \"Sep2\".employee " +
                "WHERE employeeID = ?) LIMIT 1;";
        PreparedStatement employeeStatement = null;

        try {
            employeeStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeStatement;
    }

    //Prepares a employee statement to improve DB performance for simple queries that are used multiple times
    public PreparedStatement prepareDepartmentStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".department (departmentID,departmentName) " +
                "SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT departmentID FROM \"Sep2\".department " +
                "WHERE departmentID = ?) LIMIT 1;";
        PreparedStatement departmentStatement = null;

        try {
            departmentStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentStatement;
    }

    //Uses a prepared statement and 2 String to add 1 row to the department table
    public void addDepartmentToDataBase(String departmentID, String departmentName) {
        try {
            departmentStatement.setString(1, departmentID);
            departmentStatement.setString(2, departmentName);
            departmentStatement.setString(3, departmentID);
            departmentStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addEmployeeToDataBase(Employee employee) {
        try {
            employeeStatement.setString(1, employee.getId());
            employeeStatement.setString(2, employee.getDepartmentid());
            employeeStatement.setString(3, employee.getFirstName());
            employeeStatement.setString(4, employee.getLastName());
            employeeStatement.setString(5, employee.getId());
            employeeStatement.executeUpdate();
            return true;
            
            //todo notify OTHER client
        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }

    //Departament query prints out all rows in the department table
    public void departmentQuery() {
        ArrayList<Object[]> results = new ArrayList<>();
        try {
            String departmentQuery = "SELECT * FROM \"Sep2\".department;";
            PreparedStatement departmentQueryStatement = connection.prepareStatement(departmentQuery);
            ResultSet resultSet = departmentQueryStatement.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String departmentID = row[0].toString();
                String departmentName = row[1].toString();

                System.out.println("DepartmentID: " + departmentID + " Department Name: " + departmentName);
            }
            resultSet.close();
            departmentQueryStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void employeeQuery() {
        ArrayList<Object[]> results = new ArrayList<>();
        EmployeeList employeeList = new EmployeeList();
        try {

            ResultSet resultSet = prepareEmployeeQuery().executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);

            }

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String employeeID = row[0].toString();
                String departmentID = row[1].toString();
                String firstName = row[2].toString();
                String lastName = row[3].toString();

                Employee e = new Employee(firstName, lastName, employeeID, departmentID);
                employeeList.add(e);
            }

            resultSet.close();
            prepareEmployeeQuery().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        changeSupport.firePropertyChange("EmployeeQuery",null,employeeList);


    }

    public PreparedStatement prepareEmployeeQuery() {
        String preparedStatement = "SELECT * FROM \"Sep2\".employee;";


        try {
            employeeQuery = connection.prepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeQuery;
    }


    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addItemToDataBase(StockItem stockItem) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(1989,3,5);
            System.out.println(stockItem.toString());
            stockItemStatement.setString(1, stockItem.getID());
            stockItemStatement.setString(2, stockItem.getName());
            stockItemStatement.setInt(3, stockItem.getQuantity());
            stockItemStatement.setInt(4, stockItem.getPrice());
            stockItemStatement.setDate(5,  sqlDate);
            stockItemStatement.setString(6, stockItem.getID());
            stockItemStatement.executeUpdate();
            return true;

            //todo notify OTHER client
        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }
    public PreparedStatement prepareStockItemStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".stockitem (id,name,quantity,price,expiryDate) " +
                "SELECT * FROM (SELECT ?,?,?,?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT id FROM \"Sep2\".stockitem " +
                "WHERE id = ?) LIMIT 1;";
        PreparedStatement stockItemStatement = null;

        try {
            stockItemStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItemStatement;
    }
}



