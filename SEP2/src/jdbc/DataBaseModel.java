package jdbc;

import model.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseModel {
    Connection connection;
    PreparedStatement departmentInsertStatement;
    PreparedStatement employeeInsertStatement;
    PreparedStatement employeeQuery;
    PreparedStatement stockItemInsertStatement;
    PreparedStatement stockItemQuery;
    PreparedStatement requestInsertStatement;
    PreparedStatement itemListInsertStatement;
    PreparedStatement deleteItemByIDandDep;
    PreparedStatement deleteEmployee;
    PreparedStatement addSale;
    private PropertyChangeSupport changeSupport;

    public DataBaseModel() {
        setConnection();
        departmentInsertStatement = prepareDepartmentStatement();
        employeeInsertStatement = prepareEmployeeStatement();
        stockItemQuery = prepareWHItemQuery();
        stockItemInsertStatement = prepareStockItemStatement();
        requestInsertStatement = prepareInsertRequest();
        itemListInsertStatement = prepareInsertItemListRequest();
        changeSupport = new PropertyChangeSupport(this);
        deleteItemByIDandDep = prepareDeleteItemFromWH();
        deleteEmployee = prepareDeleteEmployee();
        employeeQuery = prepareEmployeeQuery();
        addSale = prepareAddSale();


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
                "   EmployeeID varchar(30) NOT NULL PRIMARY KEY," +
                "departmentID varchar(30) NOT NULL ," +
                " firstName varchar(30) NOT NULL," +
                "lastName varchar(30) NOT NULL," +
                " FOREIGN KEY (departmentID) REFERENCES \"Sep2\".Department(departmentID)" +
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
                "   id varchar(30) NOT NULL PRIMARY KEY," +
                "name varchar(30) NOT NULL ," +
                " quantity int NOT NULL," +
                "price int NOT NULL," +
                "expiryDate Date, " +
                "location varchar(30) NOT NULL" +

                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Creates empty item request table
    public void createItemRequestTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".itemRequest (" +
                " RequestID varchar(6) NOT NULL ," +
                "itemID varchar(10) NOT NULL," +
                " quantity int NOT NULL," +
                " CONSTRAINT ItemRequest_CK PRIMARY KEY (requestID, itemID)," +
                " FOREIGN KEY (requestID) REFERENCES \"Sep2\".request(requestID)" +
                ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Creates empty  request table
    public void createRequestTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Request (" +
                " RequestID varchar(6) NOT NULL PRIMARY KEY ," +
                "requestedFrom varchar(10) NOT NULL," +
                " status varchar(30) NOT NULL" +

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
            departmentInsertStatement.setString(1, departmentID);
            departmentInsertStatement.setString(2, departmentName);
            departmentInsertStatement.setString(3, departmentID);
            departmentInsertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addEmployeeToDataBase(Employee employee) {
        try {
            employeeInsertStatement.setString(1, employee.getId());
            employeeInsertStatement.setString(2, employee.getDepartmentID());
            employeeInsertStatement.setString(3, employee.getFirstName());
            employeeInsertStatement.setString(4, employee.getLastName());
            employeeInsertStatement.setString(5, employee.getId());
            System.out.println(employee.getId());
            employeeInsertStatement.executeUpdate();
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
                //todo create department object instead sysout
            }
            resultSet.close();
            departmentQueryStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void employeeQuery(String departmentID) {
        ArrayList<Object[]> results = new ArrayList<>();
        EmployeeList employeeList = new EmployeeList();
        try {

            employeeQuery.setString(1, departmentID);
            ResultSet resultSet = employeeQuery.executeQuery();
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
                String depID = row[1].toString();
                String firstName = row[2].toString();
                String lastName = row[3].toString();

                Employee e = new Employee(firstName, lastName, employeeID, depID);
                employeeList.add(e);
            }

            resultSet.close();
            prepareEmployeeQuery().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        changeSupport.firePropertyChange("EmployeeQuery", null, employeeList);
        System.out.println("DataBaseModel: Employee query fired");


    }

    public void itemQuery(String departmentID) {
        ArrayList<Object[]> results = new ArrayList<>();
        StockItemList stockItemList = new StockItemList();
        try {

            stockItemQuery.setString(1, departmentID);
            ResultSet resultSet = stockItemQuery.executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);

            }

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String itemID = row[0].toString();
                String itemName = row[1].toString();
                int qty = (int) row[2];
                int price = (int) row[3];
                java.util.Date sqlDate = (java.sql.Date) row[4];
                String location = row[4].toString();
                java.util.Date date = new java.util.Date(sqlDate.getDay(), sqlDate.getMonth(), sqlDate.getYear());

                StockItem stockItem = new StockItem(itemName, itemID, qty, price, true, date, 10, 100, location);
                //todo max stock is not set
                stockItemList.add(stockItem);
            }
            resultSet.close();
            prepareWHItemQuery().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        changeSupport.firePropertyChange("ItemQuery", null, stockItemList);
        System.out.println("DataBaseModel: ItemQueryFired query fired");


    }

    public PreparedStatement prepareEmployeeQuery() {
        String preparedStatement = "SELECT * FROM \"Sep2\".employee where departmentID = ? ;";


        try {
            employeeQuery = connection.prepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeQuery;
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }


    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addItemToDataBase(StockItem stockItem, String department) {
        try {
            java.sql.Date sqlDate = null;
            if (stockItem.getExpiryDate() != null) {
                sqlDate = new java.sql.Date(stockItem.getExpiryDate().getDay(), stockItem.getExpiryDate().getMonth(), stockItem.getExpiryDate().getYear());
            } else {
                sqlDate = new java.sql.Date(2100, 1, 1);
            }
            stockItemInsertStatement.setString(1, stockItem.getId());
            stockItemInsertStatement.setString(2, stockItem.getName());
            stockItemInsertStatement.setInt(3, stockItem.getQuantity());
            stockItemInsertStatement.setInt(4, stockItem.getPrice());
            stockItemInsertStatement.setDate(5, sqlDate);
            stockItemInsertStatement.setString(6, department);
            stockItemInsertStatement.setString(7, stockItem.getId());
            stockItemInsertStatement.setString(8, department);

            stockItemInsertStatement.executeUpdate();
            return true;

            //todo adding Date format is not correct

        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }

    public PreparedStatement prepareStockItemStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".stockitem (id,name,quantity,price,expiryDate,location) " +
                "SELECT * FROM (SELECT ?,?,?,?,? :: DATE,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT id,location FROM \"Sep2\".stockitem " +
                "WHERE id = ? and location = ?) LIMIT 1;";
        PreparedStatement stockItemStatement = null;

        try {
            stockItemStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItemStatement;
    }


    public PreparedStatement prepareWHItemQuery() {
        String preparedStatement = "SELECT * FROM \"Sep2\".stockitem where location = ? ;";


        try {
            stockItemQuery = connection.prepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItemQuery;
    }


    public PreparedStatement prepareInsertRequest() {
        String preparedSql = "INSERT INTO \"Sep2\".request (requestID,requestedFrom,status) " +
                "SELECT * FROM (SELECT ?,?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT requestID FROM \"Sep2\".request " +
                "WHERE requestID = ?) LIMIT 1;";
        requestInsertStatement = null;

        try {
            requestInsertStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestInsertStatement;
    }

    public int addRequestToDataBase(String requestedFrom) {
        try {
            int count = requestCountQuery();
            requestInsertStatement.setString(1, "" + count);
            requestInsertStatement.setString(2, requestedFrom);
            requestInsertStatement.setString(3, "In Progress");
            requestInsertStatement.setString(4, "" + count);
            requestInsertStatement.executeUpdate();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;


        }

    }

    public int requestCountQuery() {

        int count = 1;
        try {
            String requestCount = "SELECT * FROM \"Sep2\".request;";
            PreparedStatement requestCountStatement = connection.prepareStatement(requestCount);
            ResultSet resultSet = requestCountStatement.executeQuery();
            while (resultSet.next()) {
                count++;

            }
            resultSet.close();
            requestCountStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

    public boolean addRequestItemsToDataBase(ProductRequestList productRequestList, int requestID) {
        try {
            for (int i = 0; i < productRequestList.Size(); i++) {
                ProductRequest productRequest = productRequestList.getProductRequest(i);
                itemListInsertStatement.setString(1, "" + requestID);
                itemListInsertStatement.setString(2, productRequest.getProductId());
                itemListInsertStatement.setInt(3, productRequest.getQuantity());
                itemListInsertStatement.executeUpdate();
            }

            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean addRequestItemToDatabase(ProductRequest productRequest, int requestID) {
        try {
            itemListInsertStatement.setString(1, "" + requestID);
            itemListInsertStatement.setString(2, productRequest.getProductId());
            itemListInsertStatement.setInt(3, productRequest.getQuantity());
            itemListInsertStatement.executeUpdate();


            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }


    }

    public PreparedStatement prepareInsertItemListRequest() {
        String preparedSql = "INSERT INTO \"Sep2\".itemRequest (requestID,itemID,quantity) " +
                "SELECT * FROM (SELECT ?,?,?) AS tmp ;";

        itemListInsertStatement = null;

        try {
            itemListInsertStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemListInsertStatement;
    }

    public boolean deleteItemByIdAndDepartment(String id, String department) {
        try {
            deleteItemByIDandDep.setString(1, id);
            deleteItemByIDandDep.setString(2, department);
            deleteItemByIDandDep.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }

    public PreparedStatement prepareDeleteItemFromWH() {
        String preparedSql = "DELETE FROM \"Sep2\".stockitem " +
                "where id = ? and location = ? ;";

        deleteItemByIDandDep = null;

        try {
            deleteItemByIDandDep = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteItemByIDandDep;
    }

    public PreparedStatement prepareDeleteEmployee() {
        String preparedSql = "DELETE FROM \"Sep2\".employee " +
                "where employeeID = ? ;";

        deleteEmployee = null;

        try {
            deleteEmployee = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteEmployee;
    }

    public boolean deleteEmployee(Employee employee) {
        try {


            deleteEmployee.setString(1, employee.getId());
            deleteEmployee.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public void createSalesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".sales (" +
                "   saleID varchar(10) NOT NULL ," +
                "itemID varchar(30)  ," +
                "name varchar(30)  ," +
                "quantitySold int" +
                " CONSTRAINT salesPK PRIMARY KEY (SaleID, itemID)," +
                " FOREIGN KEY (SaleID) REFERENCES \"Sep2\".salesList(SaleID)," +
                " FOREIGN KEY (itemID) REFERENCES \"Sep2\".stockItem(ID) " +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createSalesListTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".salesList (" +
                "   saleID varchar(10) NOT NULL PRIMARY KEY ," +
                "DateOfSale DATE  , " +
                " status varchar(30) " +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public PreparedStatement prepareAddSale() {
        String preparedSql = "INSERT INTO \"Sep2\".sales (productID, quantitySold) " +
                "SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT productID FROM \"Sep2\".sales " +
                "WHERE  productID = ?) LIMIT 1;";
        addSale = null;

        try {
            addSale = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addSale;
    }

    public boolean addSaleToDataBase(StockItem stockItem) {
        try {
            addSale.setString(1, stockItem.getId());
            addSale.setInt(2, stockItem.getQuantity());
            addSale.setString(3, stockItem.getId());
            addSale.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public void salesQuery() {
        ArrayList<Object[]> results = new ArrayList<>();
        StockItemList salesList = new StockItemList();
        try {
            String salesSQLQuery =
                    "SELECT  quantitySold,productID,name,price FROM \"Sep2\".sales " +
                            " LEFT JOIN \"Sep2\".stockItem on stockItem.id = sales.productID;";
            PreparedStatement salesQuery = connection.prepareStatement(salesSQLQuery);
            ResultSet resultSet = salesQuery.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                int quantitySold = (int) row[0];
                String productID = row[1].toString();
                String name = row[2].toString();
                int price = (int) row[3];

                StockItem stockItem = new StockItem(name, productID, quantitySold, price, false, null, 0, 0, null);
                salesList.add(stockItem);

            }
            changeSupport.firePropertyChange("SalesQuery", null, salesList);
            resultSet.close();
            salesQuery.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
