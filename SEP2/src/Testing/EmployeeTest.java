package Testing;


import model.Employee;
import org.junit.Assert;

public class EmployeeTest {

    // Variables needed in the test

    Employee e;

    @org.junit.Before
    public void setUp() throws Exception {
        // We set up the Employee

        e = new Employee("kenneth", "jensen", "1", "HQ");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getFirstName() {
        // We test if we can retrieve the first name

        Assert.assertEquals("kenneth", e.getFirstName());
    }

    @org.junit.Test
    public void setFirstName() {
        // We set the first name and test that it was changed

        e.setFirstName("John");
        Assert.assertEquals("John", e.getFirstName());
    }

    @org.junit.Test
    public void getLastName() {
        // We test if we can retrieve the last name

        Assert.assertEquals("kenneth", e.getFirstName());
    }

    @org.junit.Test
    public void setLastName() {
        // We set the last name and test that it was changed

        e.setLastName("hansen");
        Assert.assertEquals("hansen", e.getLastName());
    }

    @org.junit.Test
    public void getId() {
        // We test if we can retrieve the ID

        Assert.assertEquals("1", e.getId());
    }

    @org.junit.Test
    public void setId() {
        // We set the ID and test that it was changed

        e.setId("2");
        Assert.assertEquals("2", e.getId());
    }

    @org.junit.Test
    public void equals() {
        // We first test that it can tell that it is equal to itself, then that it can tell that it is
        // not equal to another Employee

        Employee em = new Employee("a", "b", "2", "HQ");
        Assert.assertTrue(e.equals(e));
        Assert.assertFalse(e.equals(em));
    }

    @org.junit.Test
    public void getDepartmentID() {
        // We test that we can retrieve the Department ID

        Assert.assertEquals("HQ", e.getDepartmentID());
    }
}