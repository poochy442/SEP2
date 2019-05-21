package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModel implements IDataModel {
    private EmployeeList employeeList;
    private StockItemList stockItemList;
    private PropertyChangeSupport changeSupport;


    public DataModel() {
        employeeList = new EmployeeList();
        changeSupport = new PropertyChangeSupport(this);
        stockItemList = new StockItemList();
    }

    PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public EmployeeList getEmployeeList() {
        return employeeList;
    }


    @Override
    public void addEmployeeToClient(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeAddedFromClient", null, e);
    }

    @Override
    public void addEmployeeToServer(Employee e) {
        employeeList.add(e);
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(evt, listener);
    }

    @Override
    public void addItemToClient(StockItem i) {
        stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("NewItemAddedFromClient", null, i);
    }

    @Override
    public void addItemToServer(StockItem i) {
        stockItemList.add(i);

    }

    public void setEmployeeList(EmployeeList employeeList) {
        this.employeeList = employeeList;
        propertyChangeSupport.firePropertyChange("NewEmployeeList",null,employeeList);
    }

    public StockItemList getStockItemList() {
        return stockItemList;
    }

    public void setStockItemList(StockItemList stockItemList) {
        this.stockItemList = stockItemList;
    }

    @Override
    public void refreshEmployeeList() {
        propertyChangeSupport.firePropertyChange("EmployeeQuery", null, null);
    }

    public boolean controlPkEmployee(String PK) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(PK))
                return false;
        }
        return true;
    }
}