package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.createSep2Schema();
        dataBaseModel.createDepartmentTable();
        dataBaseModel.createEmployeeTable();
        dataBaseModel.createStockItemTable();

        PreparedStatement departmentStatement = dataBaseModel.prepareDepartmentStatement();
        dataBaseModel.addDepartmentToDataBase("WH2","Warehouse");
        dataBaseModel.addDepartmentToDataBase("WH5","Warehouse");

        dataBaseModel.departmentQuery();


    }
}