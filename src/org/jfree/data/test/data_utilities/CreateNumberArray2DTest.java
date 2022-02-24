package org.jfree.data.test.data_utilities;

import org.jfree.data.DataUtilities;
import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

public class CreateNumberArray2DTest {

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create2DArrayWithIntValues() {
        double[][] array = new double[][]{{1, 2, 3, 4}, {5, 6, 7}};
        Number[][] actual = DataUtilities.createNumberArray2D(array);
        assertArrayEquals("Arrays should be exactly the same",
                actual, new Number[][]{{1.0, 2.0, 3.0, 4.0}, {5.0, 6.0, 7.0}});
    }

    @Test
    public void create2DArrayWithDoubleValues() {
        double[][] array = new double[][]{{1.2, 2.3, 3.5, 4.7}, {5.2, 6.3, 7.9}};
        Number[][] actual = DataUtilities.createNumberArray2D(array);
        assertArrayEquals("Arrays should be exactly the same",
                actual, new Number[][]{{1.2, 2.3, 3.5, 4.7}, {5.2, 6.3, 7.9}});
    }

    @Test
    public void create2DArrayWithMixedIntAndDoubleValues() {
        double[][] array = new double[][]{{1, 2.3, 3.5, 4.7}, {5.2, 6.3, 7}};
        Number[][] actual = DataUtilities.createNumberArray2D(array);
        assertArrayEquals("Arrays should be exactly the same",
                actual, new Number[][]{{1.0, 2.3, 3.5, 4.7}, {5.2, 6.3, 7.0}});
    }

    @Test
    public void create2DArrayWithEmptyArrays() {
        double[][] array = new double[][]{{}, {}};
        Number[][] actual = DataUtilities.createNumberArray2D(array);
        assertArrayEquals("Arrays should be exactly the same",
                actual, new Number[][]{{}, {}});
    }

    @Test(expected = IllegalArgumentException.class)
    public void create2DArrayWithNullParameter() {
        DataUtilities.createNumberArray2D(null);
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}
