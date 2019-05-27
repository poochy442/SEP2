package Testing;

import model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class DataModelTest {

    // The variables needed for this test, to be set up in the Setup() method

    DataModel dataModel;
    EmployeeList eList;
    StockItemList sList;

    @Before
    public void setUp() throws Exception {
        // In the setup we set up the DataModel we are going to be testing, 2 Employees in a List
        // and a Stock Item in a list

        dataModel = new DataModel();
        eList = new EmployeeList();
        sList = new StockItemList();
        Employee e1 = new Employee("Kenneth", "Jensen", "1", "1");
        Employee e2 = new Employee("Florin", "Bordei", "2", "1");
        eList.add(e1);
        eList.add(e2);
        StockItem s1 = new StockItem("Banana", "1",
                100, 5,
                false, new Date(1, 1, 1),
                5, 500, "WH");
        sList.add(s1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getEmployeeList() {
        // We test if we can call a method in the EmployeeList, by testing if the size = 0

        Assert.assertTrue(dataModel.getEmployeeList().size() == 0);
    }

    @Test
    public void addEmployeeFromUser() {
        // We add an employee from our List and then test if the size of the List is 1

        dataModel.addEmployeeFromUser(eList.get(0));
        Assert.assertTrue(dataModel.getEmployeeList().size() == 1);
    }

    @Test
    public void addEmployeeFromServer() {
        // We add an employee from our List and then test if the size of the List is 1

        dataModel.addEmployeeFromServer(eList.get(0));
        Assert.assertTrue(dataModel.getEmployeeList().size() == 1);
    }

    @Test
    public void addItemFromUser() {
        // We add a Stock Item from our List and then test if the size of the List is 1

        dataModel.addItemFromUser(sList.get(0));
        Assert.assertTrue(dataModel.getStockItemList().size() == 1);
    }

    @Test
    public void addItemFromServer() {
        // We add a Stock Item from our List and then test if the size of the List is 1

        dataModel.addItemFromUser(sList.get(0));
        Assert.assertTrue(dataModel.getStockItemList().size() == 1);
    }

    @Test
    public void setEmployeeList() {
        // We set the DataModel's EmployeeList equal to eList and then test whether the two tables are now equal
        // each other

        dataModel.setEmployeeList(eList);
        Assert.assertEquals(eList, dataModel.getEmployeeList());
    }

    @Test
    public void getStockItemList() {
        // We test that we can call a method in the StockItemList by testing if the size = 0

        Assert.assertTrue(dataModel.getStockItemList().size() == 0);
    }

    @Test
    public void setStockItemList() {
        // We set the DataModel's StockItemList equal to sList and then test whether the two tables are now equal
        // each other

        dataModel.setStockItemList(sList);
        Assert.assertEquals(sList, dataModel.getStockItemList());
    }
}