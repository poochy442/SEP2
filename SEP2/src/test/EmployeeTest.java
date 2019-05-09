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
    }

    @org.junit.Test
    public void setLastName() {
    }

    @org.junit.Test
    public void getId() {
    }

    @org.junit.Test
    public void setId() {
    }

    @org.junit.Test
    public void equals() {
        Assert.assertTrue(e.equals(e));
    }
}