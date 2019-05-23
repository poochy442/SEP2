package jdbc;

import model.ProductRequest;
import model.ProductRequestList;
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
        dataBaseModel.departmentQuery();
        dataBaseModel.addRequestToDataBase("WH");
        ProductRequestList productRequestList = new ProductRequestList();
        StockItem stockItem1= new StockItem("banana","333",3,3,true,date,3,10);
        ProductRequest p1 = new ProductRequest(stockItem,5);
        ProductRequest p2 = new ProductRequest(stockItem1,10);
        productRequestList.addRequestToList(p1);
        productRequestList.addRequestToList(p2);
        System.out.println(dataBaseModel.addRequestItemsToDataBase(productRequestList,1));
        System.out.println(productRequestList.getSize());
    }
}