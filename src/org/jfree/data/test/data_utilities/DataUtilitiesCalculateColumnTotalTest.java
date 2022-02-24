package org.jfree.data.test.data_utilities;

import org.jfree.data.DataUtilities;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jfree.data.Values2D;
import org.junit.*;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class DataUtilitiesCalculateColumnTotalTest {
    private static Mockery mockingContext;
    private static Values2D values;
    private final double DELTA = .000000001d;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
        mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
    }

    @Test
    public void calculateColumnTotalOfOneRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(1));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The calculated sum should be 5.1", 5.1, result, DELTA);
    }

    @Test
    public void calculateColumnTotalOfZeroRows() {

        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The calculated sum should be 0", 0, result, DELTA);
    }

    @Test
    public void calculateColumnTotalOfTwoRows() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(2));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
            one(values).getValue(1, 0);
            will(returnValue(3.2));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The calculated sum should be 8.3 for column 0", 8.3, result, DELTA);
    }

    @Test
    public void calculateColumnTotalOfTwoRowsWithDifferentIndex() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(2));
            one(values).getValue(0, 1);
            will(returnValue(5.1));
            one(values).getValue(1, 1);
            will(returnValue(3.2));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals("The calculated sum should be 8.3 for column 1", 8.3, result, DELTA);
    }

    @Test
    public void calculateColumnTotalOfOneRowWithNullValue() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(1));
            one(values).getValue(0, 0);
            will(returnValue(null));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The calculated sum for null value should be 0", 0, result, DELTA);
    }

    @Test
    public void calculateColumnTotalOfMultipleRowsWhenNullIsIncluded() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(3));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
            one(values).getValue(1, 0);
            will(returnValue(null));
            one(values).getValue(2, 0);
            will(returnValue(-1.3));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The calculated sum for null value should be 3.8", 3.8, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotalWithNullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotalForIndexLowerThanZero() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(2));
            one(values).getValue(0, -1);
            will(throwException(new java.lang.IndexOutOfBoundsException()));
        }});
        DataUtilities.calculateColumnTotal(values, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotalForIndexBiggerThanDataColumnSize() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(1));
            one(values).getValue(0, 2);
            will(throwException(new java.lang.IndexOutOfBoundsException()));
        }});

        DataUtilities.calculateColumnTotal(values, 2);
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}