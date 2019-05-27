//public void myArrayTest()    {
//        TestCases testCases = new TestCases();
//        List<String> result = testCases.myArray();
//        Assert.assertNotNull("List shouldn't be null", result);
//        Assert.assertEquals("wrong size", 3, result.size());
//        Assert.assertEquals("Wrong 1st element", "Customer1", result.get(0));
//        Assert.assertEquals("Wrong 2nd element", "Customer2", result.get(1));
//        Assert.assertEquals("Wrong 3rd element", "Customer3", result.get(2));


package Testing;

import model.Employee;
import model.EmployeeList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListTest {

    // Variables needed in the test

    Employee e;
    Employee f;
    EmployeeList test;

    @Before
    public void setUp() throws Exception {
        // Here we set up the List and 2 Employees

        test = new EmployeeList();
        e = new Employee("Kevin", "Johny", "12", "HQ");
        f = new Employee("Dave", "Bob", "123", "WH");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        // We add an Employee and test if the Employee were added to the list

        test.add(e);
        Assert.assertEquals(e, test.get(0));
    }

    @Test
    public void add1() {
        // We add a List of Employees and test that both Employees were added to the list

        List<Employee> employees = new ArrayList<>();
        employees.add(e);
        employees.add(f);
        test.add(employees);
        Assert.assertEquals(test.get(1).getId(), "123");

    }

    @Test
    public void remove() {
        // We add an employee to the list, remove it by passing the Employee, and test that it was removed

        test.add(e);
        test.remove(e);
        boolean size = false;
        Assert.assertTrue(test.size() == 0);
    }

    @Test
    public void remove1() {
        // We add an Employee to the list and remove it by passing the index. and test that it was remove

        test.add(e);
        test.remove(0);
        boolean size = false;
        Assert.assertTrue(test.size() == 0);
    }

    @Test
    public void get() {
        // We add an Employee to the list and test whether we can retrieve it

        test.add(e);
        Assert.assertEquals(test.get(0), e);

    }

    @Test
    public void size() {
        // We add 2 Employees to the table and test whether we get the correct size back

        test.add(e);
        test.add(f);
        Assert.assertEquals(test.size(), 2);
    }

}