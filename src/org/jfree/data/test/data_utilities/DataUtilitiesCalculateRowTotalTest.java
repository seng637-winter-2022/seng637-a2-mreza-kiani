package org.jfree.data.test.data_utilities;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class DataUtilitiesCalculateRowTotalTest {
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
    public void calculateRowTotalOfOneColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(1));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated sum should be 5.1", 5.1, result, DELTA);
    }

    @Test
    public void calculateRowTotalOfZeroColumns() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(0));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated sum should be 0", 0, result, DELTA);
    }

    @Test
    public void calculateRowTotalOfTwoColumns() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(2));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
            one(values).getValue(0, 1);
            will(returnValue(3.2));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated sum should be 8.3 for column 0", 8.3, result, DELTA);
    }

    @Test
    public void calculateRowTotalOfTwoColumnsWithDifferentIndex() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(2));
            one(values).getValue(1, 0);
            will(returnValue(5.1));
            one(values).getValue(1, 1);
            will(returnValue(3.2));
        }});

        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals("The calculated sum should be 8.3 for column 1", 8.3, result, DELTA);
    }

    @Test
    public void calculateRowTotalOfOneColumnWithNullValue() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(1));
            one(values).getValue(0, 0);
            will(returnValue(null));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated sum for null value should be 0", 0, result, DELTA);
    }

    @Test
    public void calculateRowTotalOfMultipleColumnsWhenNullIsIncluded() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(3));
            one(values).getValue(0, 0);
            will(returnValue(5.1));
            one(values).getValue(0, 1);
            will(returnValue(null));
            one(values).getValue(0, 2);
            will(returnValue(-1.3));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated sum for null value should be 3.8", 3.8, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateRowTotalWithNullData() {
        DataUtilities.calculateRowTotal(null, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotalForIndexLowerThanZero() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(2));
            one(values).getValue(-1, 0);
            will(throwException(new IndexOutOfBoundsException()));
        }});
        DataUtilities.calculateRowTotal(values, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotalForIndexBiggerThanDataRowSize() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount();
            will(returnValue(1));
            one(values).getValue(2, 0);
            will(throwException(new IndexOutOfBoundsException()));
        }});

        DataUtilities.calculateRowTotal(values, 2);
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}