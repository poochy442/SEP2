package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModel implements IDataModel {
    private EmployeeList employeeList;
    private StockItemList stockItemList;


    public DataModel() {
        employeeList = new EmployeeList();
        stockItemList = new StockItemList();
    }

    PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public EmployeeList getEmployeeList() {
        return employeeList;
    }


    @Override
    public void addEmployeeToDB(Employee e) {
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
    public void storeItemInDB(StockItem i) {
        //stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("ItemToDB", null, i);
    }

    @Override
    public void addItemToServer(StockItem i) {
        stockItemList.add(i);

    }

    public void setEmployeeList(EmployeeList employeeList) {
        this.employeeList = employeeList;
        propertyChangeSupport.firePropertyChange("NewEmployeeList",null,employeeList);
    }

    @Override
    public void refreshEmployeeList() {
        propertyChangeSupport.firePropertyChange("EmployeeQuery",null,null);

    }

    public StockItemList getStockItemList() {
        return stockItemList;
    }

    public void setStockItemList(StockItemList stockItemList) {
        this.stockItemList = stockItemList;
    }


}