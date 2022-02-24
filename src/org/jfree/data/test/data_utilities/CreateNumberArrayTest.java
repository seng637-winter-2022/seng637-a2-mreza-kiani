package org.jfree.data.test.data_utilities;

import org.jfree.data.DataUtilities;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

public class CreateNumberArrayTest {

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createNumberArrayWithIntValues() {
        Number[] actual = DataUtilities.createNumberArray(new double[]{1, 2, 3});
        assertArrayEquals("The returned array should be exactly the same",
                new Number[]{1.0, 2.0, 3.0}, actual);
    }

    @Test
    public void createNumberArrayWithDoubleValues() {
        Number[] actual = DataUtilities.createNumberArray(new double[]{1.2, 2.3, 3.9});
        assertArrayEquals("The returned array should be exactly the same",
                new Number[]{1.2, 2.3, 3.9}, actual);
    }

    @Test
    public void createNumberArrayWithIntAndDoubleMixedValues() {
        Number[] actual = DataUtilities.createNumberArray(new double[]{1, 2.3, 3.9});
        assertArrayEquals("The returned array should be exactly the same",
                new Number[]{1.0, 2.3, 3.9}, actual);
    }

    @Test
    public void createNumberArrayWithEmptyArray() {
        Number[] actual = DataUtilities.createNumberArray(new double[]{});
        assertArrayEquals("The returned array should be exactly the same",
                new Number[]{}, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArrayWithNullValue() {
        DataUtilities.createNumberArray(null);
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}
