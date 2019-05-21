package model;

import java.beans.PropertyChangeListener;

public interface IDataModel {
    void addEmployeeToClient (Employee e);
    void addEmployeeToServer (Employee e);
    void addListener(String evt, PropertyChangeListener listener);
    void addItemToClient (StockItem i);
    void addItemToServer (StockItem i);
    EmployeeList getEmployeeList();
    void setEmployeeList(EmployeeList employeeList);
    void refreshEmployeeList();
}
