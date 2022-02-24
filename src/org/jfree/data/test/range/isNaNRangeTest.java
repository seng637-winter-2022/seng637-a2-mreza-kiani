package org.jfree.data.test.range;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class isNaNRangeTest{

	@BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void lowerBoundNaNTest() {
    	
    	Range r1 = new Range(Double.NaN, 1);
    	
    	assertFalse(r1.isNaNRange());
    }
    
    @Test
    public void upperBoundNaNTest() {
    	
    	Range r1 = new Range(1,Double.NaN);
    	
    	assertFalse(r1.isNaNRange());
    }
    
    @Test
    public void bothBoundsNaN() {
    	
    	Range r1 = new Range(Double.NaN,Double.NaN);
    	
    	assertTrue(r1.isNaNRange());
    }
    
    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
}
