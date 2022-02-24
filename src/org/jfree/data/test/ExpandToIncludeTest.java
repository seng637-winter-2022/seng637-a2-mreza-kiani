package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class ExpandToIncludeTest{
	
	@BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
    }
	
	@Test
	public void nullRange() {
		
		Range r1 = null;
    	Range result = Range.expandToInclude(r1, 10);
    	Range expected = new Range(10,10);
    	assertEquals(result,expected);
	}
	
	@Test
	public void valueGreaterThanUpperbound() {
		Range r1 = new Range(0,1);
		
    	Range result = Range.expandToInclude(r1, 10);
    	Range expected = new Range(0,10);
    	assertEquals(result,expected);
	}
	
	@Test
	public void valueLessThanLowerBound() {
		Range r1 = new Range(0,1);
		
    	Range result = Range.expandToInclude(r1, -1);
    	Range expected = new Range(-1,1);
    	assertEquals(result,expected);
	}
	
	@Test
	public void valueWithinRange() {
		Range r1 = new Range(0,10);
		
    	Range result = Range.expandToInclude(r1, 5);
    	Range expected = new Range(0,10);
    	assertEquals(result,expected);
	}
	
	@After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

}
