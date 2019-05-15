package model;

import java.beans.PropertyChangeListener;

public interface IDataModel {
    void addEmployeeToDB(Employee e);
    void addEmployeeToServer (Employee e);
    void addListener(String evt, PropertyChangeListener listener);
    void storeItemInDB(StockItem i);
    void addItemToServer (StockItem i);
    EmployeeList getEmployeeList();
    void setEmployeeList(EmployeeList employeeList);
    void refreshEmployeeList();

}
