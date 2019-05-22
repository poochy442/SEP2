package jdbc;

import model.StockItem;

import java.sql.Date;
import java.sql.PreparedStatement;

public class DataBase {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.createSep2Schema();
        dataBaseModel.createDepartmentTable();
        dataBaseModel.createEmployeeTable();
        dataBaseModel.createStockItemTable();
        Date date = new Date(1,1,1);
        StockItem stockItem = new StockItem("Bonano","5554",3,3,true,date,3,10);
        System.out.println(dataBaseModel.addItemToDataBase(stockItem));
        PreparedStatement departmentStatement = dataBaseModel.prepareDepartmentStatement();
        dataBaseModel.addDepartmentToDataBase("WH2","Warehouse");
        dataBaseModel.addDepartmentToDataBase("WH5","Warehouse");
        dataBaseModel.departmentQuery();
    }
}