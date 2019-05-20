package test;

import model.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {
    Date test;

    @Before
    public void setUp() throws Exception {
        test = new Date(21, 9, 1987);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDay() {
        assertEquals(21, test.getDay());

    }

    @Test
    public void setDay() {
        test.setDay(22);
        assertEquals(22, test.getDay());
    }

    @Test
    public void getMonth() {

        assertEquals(9, test.getMonth());
    }

    @Test
    public void setMonth() {
        test.setMonth(7);
        assertEquals(7, test.getMonth());
    }

    @Test
    public void getYear() {
        assertEquals(1987, test.getYear());
    }

    @Test
    public void setYear() {
        test.setYear(1999);
        assertEquals(1999, test.getYear());
    }

    @Test
    public void daysInMonth() {
        int length = test.getMonth();
assertEquals(9, length);

    }

    @Test
    public void isLeapYear() {
        assertFalse(String.valueOf(false), test.isLeapYear(1999));



    }

    @Test
    public void incDay() {
        test.setDay(11);
test.incDay();
assertEquals(12, test.getDay());
    }

    @Test
    public void incDays() {
        test.setDay(30);
        test.setYear(1998);
        test.setMonth(12);
        Date test2 = new Date(1, 1, 1999);
        test.incDays(2);

        assertEquals(true, test.equals(test2));

    }

    @Test
    public void equals1() {
        Date j = new Date(21,12,1997);
        assertTrue(test.equals(test));
        assertFalse(test.equals(j));


    }
}