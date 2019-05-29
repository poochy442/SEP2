package model;

import java.beans.PropertyChangeListener;

public interface IDataModel {
    void addEmployeeFromUser(Employee e);

    void addEmployeeFromServer(Employee e);

    void addListener(String evt, PropertyChangeListener listener);

    void addItemFromServer(StockItem i);

    void addItemFromUser(StockItem i);

    EmployeeList getEmployeeList();

    void setEmployeeList(EmployeeList employeeList);

    void loadEmployeeListFromDB(String departmentID);

    void loadItemListFromDB(String departmentID);

    void setStockItemList(StockItemList stockItemList1);

    void sendProductRequest();

    void removeStockItemWH(StockItem stockItem);

    void removeStockItemHQ(StockItem stockItem);

    void removeEmployeeWH(Employee e);

    void removeEmployeeHQ(Employee e);

    String getIDEmployee();

    String getIDStockItem();

    boolean onlyLetters(String word);

    boolean onlyNumbers(String word);

    void addToProductRequest(ProductRequest productRequest,boolean notifyServer);

    void addToSales(StockItem selectedItem, boolean notifyServer);

    void loadSalesFromDB();
}
