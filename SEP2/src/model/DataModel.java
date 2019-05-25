package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

public class DataModel implements IDataModel {
    private EmployeeList employeeList;
    private StockItemList stockItemList;
    private PropertyChangeSupport propertyChangeSupport;
    private ProductRequestList productRequestList;


    public DataModel() {
        employeeList = new EmployeeList();
        propertyChangeSupport = new PropertyChangeSupport(this);
        stockItemList = new StockItemList();
        productRequestList = new ProductRequestList();
    }


    public EmployeeList getEmployeeList() {
        return employeeList;
    }


    @Override
    public void addEmployeeFromUser(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeFromUser", null, e);


    }

    @Override
    public void addEmployeeFromServer(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeFromServer", null, e);

    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(evt, listener);
    }

    @Override
    public void addItemFromServer(StockItem i) {
        stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("NewItemFromServer", null, i);
    }

    @Override
    public void addItemFromUser(StockItem i) {
        stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("NewItemFromUser", null, i);

    }

    public void setEmployeeList(EmployeeList employeeList) {
        this.employeeList = employeeList;
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("DataModel " + employeeList.get(i).getFirstName());
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
    public void sendProductRequest() {
        StockItem stockItem = new StockItem("PlayStation3", "2", 2, 5, false, new Date(3, 3, 3), 1, 5);
        ProductRequest productRequest = new ProductRequest(stockItem, 22);
        productRequestList.addRequestToList(productRequest);
        propertyChangeSupport.firePropertyChange("SendProductRequest", null, productRequest);
    }

    @Override
    public void deleteStockItemWH(StockItem stockItem) {
        for (int i=0;i<stockItemList.size();i++)
        {
            if (stockItem.getId().equals(stockItemList.get(i).getId()))
            {
                stockItemList.remove(i);
            }

        }
        propertyChangeSupport.firePropertyChange("DeleteStockItemFromDB",null,stockItem);
    }

    @Override
    public void deleteStockItemHQ(StockItem stockItem) {

    }

    @Override
    public void loadEmployeeListFromDB() {
        propertyChangeSupport.firePropertyChange("EmployeeQuery", 0, 2);
        System.out.println("DataModel refresh Employee list query");
    }

    @Override
    public void loadItemListFromDB() {
        propertyChangeSupport.firePropertyChange("ItemQuery", 0, 2);
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