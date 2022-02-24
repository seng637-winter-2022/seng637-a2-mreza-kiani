package org.jfree.data.test.range;

import org.jfree.data.Range;
import org.junit.*;

import static org.junit.Assert.*;

public class IntersectMethodTest {
    private Range range;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
        range = new Range(-1, 1);
    }

    @Test
    public void aFullyIntersectionForNumbers() {
        assertTrue("[-0.5, +0.5] has intersection with [-1, +1]", range.intersects(-0.5, 0.5));
    }

    @Test
    public void aFullyIntersectionForRange() {
        assertTrue("[-2, +2] has intersection with [-1, +1]", range.intersects(-2, 2));
    }

    @Test
    public void aPartialIntersectionWhenNumbersAreOnTheLeft() {
        assertTrue("[-1.5, +0.5] has intersection with [-1, +1]", range.intersects(-1.5, 0.5));
    }

    @Test
    public void aPartialIntersectionWhenNumbersAreOnTheRight() {
        assertTrue("[-0.5, +1.5] has intersection with [-1, +1]", range.intersects(-0.5, 1.5));
    }

    @Test
    public void noIntersection() {
        assertFalse("[-2, -1.5] has no intersection with [-1, +1]", range.intersects(-2, -1.5));
    }

    @Test
    public void anInvalidIntersection() {
        assertFalse("[0.5, -0.5] is an invalid range", range.intersects(0.5, -0.5));
    }

    @Test
    public void aMarginalIntersection() {
        assertFalse("[-2, -1] has a marginal intersection with [-1, +1]", range.intersects(-2, -1));
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}