package org.jfree.data.test.data_utilities;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class GetCumulativePercentagesTest {

    private static Mockery mockingContext;
    private KeyedValues values;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
        mockingContext = new Mockery();
        values = mockingContext.mock(KeyedValues.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentageForNullParameter() {
        KeyedValues actual = DataUtilities.getCumulativePercentages(null);
    }

    //Key values are:
    // 0, 5
    @Test
    public void getCumulativeTestForOneElement() {
        mockingContext.checking(new Expectations() {{
            atLeast(1).of(values).getItemCount();
            will(returnValue(1));
            atLeast(1).of(values).getValue(0);
            will(returnValue(24));

            one(values).getKey(0);
            will(returnValue(0));
        }});

        KeyedValues actual = DataUtilities.getCumulativePercentages(values);

        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue(Integer.valueOf(0), 1);

        assertEquals("Asserting for one element in keyedValues", expected, actual);
    }

    //Key values are:
    // 1: 4
    // 2: 5
    // 3: 6
    @Test
    public void getCumulativeTestForMultipleElements() {
        mockingContext.checking(new Expectations() {{
            atLeast(1).of(values).getItemCount();
            will(returnValue(3));

            atLeast(1).of(values).getValue(0);
            will(returnValue(4));
            atLeast(1).of(values).getValue(1);
            will(returnValue(5));
            atLeast(1).of(values).getValue(2);
            will(returnValue(6));

            atLeast(1).of(values).getKey(0);
            will(returnValue(1));
            atLeast(1).of(values).getKey(1);
            will(returnValue(2));
            atLeast(1).of(values).getKey(2);
            will(returnValue(3));
        }});

        KeyedValues actual = DataUtilities.getCumulativePercentages(values);

        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue(Integer.valueOf(1), 4.0 / 15.0);
        expected.addValue(Integer.valueOf(2), (4.0 + 5.0) / 15.0);
        expected.addValue(Integer.valueOf(3), (4.0 + 5.0 + 6.0) / 15.0);

        assertEquals("Asserting for multiple elements in keyedValues",
                expected, actual);
    }

    //Key values are:
    // 1: 4
    // 2: null
    // 3: 6
    @Test
    public void getCumulativeTestContainingNullValue() {
        mockingContext.checking(new Expectations() {{
            atLeast(1).of(values).getItemCount();
            will(returnValue(3));

            atLeast(1).of(values).getValue(0);
            will(returnValue(4));
            atLeast(1).of(values).getValue(1);
            will(returnValue(null));
            atLeast(1).of(values).getValue(2);
            will(returnValue(6));

            atLeast(1).of(values).getKey(0);
            will(returnValue(1));
            atLeast(1).of(values).getKey(1);
            will(returnValue(2));
            atLeast(1).of(values).getKey(2);
            will(returnValue(3));
        }});

        KeyedValues actual = DataUtilities.getCumulativePercentages(values);

        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue(Integer.valueOf(1), 4.0 / 10.0);
        expected.addValue(Integer.valueOf(2), (4.0) / 10.0);
        expected.addValue(Integer.valueOf(3), (4.0 + 6.0) / 10.0);

        assertEquals("Asserting for null in values in keyedValues",
                expected, actual);

    }

    //Key values are:
    // 1: 4
    // null: 5
    // 3: 6
    @Test(expected = IllegalArgumentException.class)
    public void getCumulativeTestContainingNullKey() {
        mockingContext.checking(new Expectations() {{
            atLeast(1).of(values).getItemCount();
            will(returnValue(3));

            atLeast(1).of(values).getValue(0);
            will(returnValue(4));
            atLeast(1).of(values).getValue(1);
            will(returnValue(5));
            atLeast(1).of(values).getValue(2);
            will(returnValue(6));

            atLeast(1).of(values).getKey(0);
            will(returnValue(1));
            atLeast(1).of(values).getKey(1);
            will(returnValue(null));
            atLeast(1).of(values).getKey(2);
            will(returnValue(3));
        }});

        KeyedValues actual = DataUtilities.getCumulativePercentages(values);
    }

    @Test(expected = Exception.class)
    public void getCumulativeTestContainingNegative() {

        // setup
        mockingContext.checking(new Expectations() {{
            atLeast(1).of(values).getItemCount();
            will(returnValue(3));

            atLeast(1).of(values).getValue(0);
            will(returnValue(4));
            atLeast(1).of(values).getValue(1);
            will(returnValue(-5));
            atLeast(1).of(values).getValue(2);
            will(returnValue(6));

            atLeast(1).of(values).getKey(0);
            will(returnValue(1));
            atLeast(1).of(values).getKey(1);
            will(returnValue(2));
            atLeast(1).of(values).getKey(2);
            will(returnValue(3));
        }});

        KeyedValues actual = DataUtilities.getCumulativePercentages(values);
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}
