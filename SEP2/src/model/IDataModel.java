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
    void loadEmployeeListFromDB();
    void loadItemListFromDB();

    void setStockItemList(StockItemList stockItemList1);

    void sendProductRequest();
}
