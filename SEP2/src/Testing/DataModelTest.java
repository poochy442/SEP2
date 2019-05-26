package Testing;

import model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class DataModelTest {

    DataModel dataModel;
    Employee e;
    Employee f;
    StockItem i;

    @Before
    public void setUp() throws Exception {
        dataModel = new DataModel();
        e = new Employee("kenneth", "jensen", "1", "HQ");
        f = new Employee("Dave", "Bob", "123", "WH");
        Date date = new Date();
        i = new StockItem("banana", "500", -500, 6876, false, date, 50, 60);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getEmployeeList() {
        EmployeeList emplooyeeListTest = new EmployeeList();
        emplooyeeListTest.add(e);
        emplooyeeListTest.add(f);
        dataModel.setEmployeeList(emplooyeeListTest);
        Assert.assertEquals(emplooyeeListTest, dataModel.getEmployeeList());
    }

    @Test
    public void addEmployeeToClient() {
        for(int i = 0; i < dataModel.getEmployeeList().size(); i++)
        {
            dataModel.getEmployeeList().remove(i);
        }
        dataModel.addEmployeeFromUser(e);
        boolean biggerSize = false;
        if(dataModel.getEmployeeList().size() > 0)
        {
            biggerSize = true;
        }
        Assert.assertTrue(biggerSize);
    }

    @Test
    public void addEmployeeToServer() {
        for(int i = 0; i < dataModel.getEmployeeList().size(); i++)
        {
            dataModel.getEmployeeList().remove(i);
        }
        dataModel.addEmployeeFromServer(e);
        boolean biggerSize = false;
        if(dataModel.getEmployeeList().size() > 0)
        {
            biggerSize = true;
        }
        Assert.assertTrue(biggerSize);
    }

    @Test
    public void addListener() {

    }

    @Test
    public void addItemToClient() {
        for(int i = 0; i < dataModel.getStockItemList().size(); i++) {
            dataModel.getStockItemList().remove(i);
        }
        dataModel.addItemFromServer(i);
        Assert.assertNotNull(dataModel.getStockItemList());
    }

    @Test
    public void addItemToServer() {
        for(int i = 0; i < dataModel.getStockItemList().size(); i++) {
            dataModel.getStockItemList().remove(i);
        }
        dataModel.addItemFromUser(i);
        Assert.assertNotNull(dataModel.getStockItemList());
    }

    @Test
    public void setEmployeeList() {
        EmployeeList employeeList = new EmployeeList();
        EmployeeList employeeListTest = new EmployeeList();
        employeeList.add(e);
        employeeListTest.add(e);
        dataModel.setEmployeeList(employeeListTest);
        Assert.assertEquals(employeeList.size(), dataModel.getEmployeeList().size());
    }

    @Test
    public void getStockItemList() {
        StockItemList stockItemListTest = new StockItemList();
        stockItemListTest.add(i);
        dataModel.setStockItemList(stockItemListTest);
        Assert.assertEquals(stockItemListTest, dataModel.getStockItemList());
    }

    @Test
    public void setStockItemList() {
        StockItemList stockItemList = new StockItemList();
        StockItemList stockItemListTest = new StockItemList();
        stockItemList.add(i);
        stockItemList.add(i);
        dataModel.setStockItemList(stockItemList);
        Assert.assertEquals(stockItemList.size(), dataModel.getStockItemList().size());
    }
}