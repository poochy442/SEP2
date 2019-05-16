package test;

import model.Employee;
import org.junit.Assert;

public class EmployeeTest {

    Employee e;

    @org.junit.Before
    public void setUp() throws Exception {
        e = new Employee("kenneth", "jensen", "1");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getFirstName() {
        Assert.assertEquals(e.getFirstName(), "kenneth");
    }

    @org.junit.Test
    public void setFirstName() {
        e.setFirstName("John");
        Assert.assertEquals(e.getFirstName(), "John");
    }

    @org.junit.Test
    public void getLastName() {
        Assert.assertEquals("kenneth", e.getFirstName());
    }

    @org.junit.Test
    public void setLastName() {
        e.setLastName("hansen");
        Assert.assertEquals("hansen", e.getLastName());
    }

    @org.junit.Test
    public void getId() {
        Assert.assertEquals("1", e.getId());
    }

    @org.junit.Test
    public void setId() {
        e.setId("2");
        Assert.assertEquals("2", e.getId());
    }

    @org.junit.Test
    public void equals() {
        Employee em = new Employee("a", "b", "2");
        Assert.assertTrue(e.equals(e));
        Assert.assertFalse(e.equals(em));
    }
}