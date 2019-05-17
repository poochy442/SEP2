package model;

import java.beans.PropertyChangeListener;

public interface IDataModel {
    void addEmployee(Employee e);
    void addEmployeeToServer (Employee e);
    void addListener(String evt, PropertyChangeListener listener);
    void addItemToDB(StockItem i);
    void addItemToServer (StockItem i);
    EmployeeList getEmployeeList();
    void setEmployeeList(EmployeeList employeeList);
    void refreshEmployeeList();

}
