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

    Employee e;
    Employee f;
    EmployeeList test = new EmployeeList();

    @Before
    public void setUp() throws Exception {
    e = new Employee("Kevin", "Johny", "12", "HQ");
    f = new Employee("Dave", "Bob", "123", "WH");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        test.add(e);
        Assert.assertEquals(e, test.get(0));
        System.out.println(test.get(0));

    }

    @Test
    public void add1() {
        List<Employee> employees = new ArrayList<>();
        employees.add(e);
        employees.add(f);
        test.add(employees);
        Assert.assertEquals(test.get(1).getId(), "123");

    }

    @Test
    public void remove() {
        test.add(e);
        test.remove(e);
        boolean size = false;
        if(test.size() == 0)
        {
            size = true;
        }
        Assert.assertTrue(size);
    }

    @Test
    public void remove1() {
        test.add(e);
        test.remove(0);
        boolean size = false;
        if(test.size() == 0)
        {
            size = true;
        }
        Assert.assertTrue(size);
    }

    @Test
    public void get() {
        EmployeeList el = new EmployeeList();
        el.add(e);
        Assert.assertEquals(el.get(0), e);

    }

    @Test
    public void size() {
        test.add(e);
        test.add(f);
        Assert.assertEquals(test.size(), 2);
    }

}