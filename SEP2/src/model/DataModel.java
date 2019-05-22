package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModel implements IDataModel {
    private EmployeeList employeeList;
    private StockItemList stockItemList;
    private PropertyChangeSupport propertyChangeSupport;


    public DataModel() {
        employeeList = new EmployeeList();
        propertyChangeSupport = new PropertyChangeSupport(this);
        stockItemList = new StockItemList();
    }


    public EmployeeList getEmployeeList() {
        return employeeList;
    }


    @Override
    public void addEmployeeToClient(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeAddedToClient", null, e);


    }

    @Override
    public void addEmployeeToServer(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeAddedFromClient", null, e);
        propertyChangeSupport.firePropertyChange("NewEmployeeAddedToClient", null, e);

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
        for (int i=0;i<employeeList.size();i++)
        {
            System.out.println("DataModel "+employeeList.get(i).getFirstName());
        }

        propertyChangeSupport.firePropertyChange("NewEmployeeList", null, employeeList);
    }

    public StockItemList getStockItemList() {
        return stockItemList;
    }

    public void setStockItemList(StockItemList stockItemList) {
        this.stockItemList = stockItemList;
        System.out.println("DataModel: ItemList Received from server and stored");

        propertyChangeSupport.firePropertyChange("NewStockItemList", null, stockItemList);
    }

    @Override
    public void loadEmployeeListFromDB() {
    propertyChangeSupport.firePropertyChange("EmployeeQuery",0,2);
        System.out.println("DataModel refresh Employee list query");
    }

    @Override
    public void loadItemListFromDB() {
        propertyChangeSupport.firePropertyChange("ItemQuery",0,2);
        System.out.println("DataModel refresh Item list query");
    }


    public boolean controlPkEmployee(String PK) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(PK))
                return false;
        }
        return true;
    }
}