package jdbc;

import java.sql.PreparedStatement;

public class DataBase {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.createSep2Schema();
        dataBaseModel.createDepartmentTable();
        dataBaseModel.createEmployeeTable();
        dataBaseModel.createStockItemTable();
        dataBaseModel.itemQuery();
        PreparedStatement departmentStatement = dataBaseModel.prepareDepartmentStatement();
        dataBaseModel.addDepartmentToDataBase("WH2","Warehouse");
        dataBaseModel.addDepartmentToDataBase("WH5","Warehouse");
        dataBaseModel.departmentQuery();
    }
}