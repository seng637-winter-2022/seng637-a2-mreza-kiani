package org.jfree.data.test.range;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class ShiftTest {
	
	@BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() throws Exception {
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void shiftWithNullBaseValue() {
		Range r1 = null;
		
		Range result = Range.shift(r1, 0, false);
		
	}
	
	@Test
	public void shiftWithZeroCrossingTrue() {
		Range r1 = new Range(-1,0);
		double delta = 2;
		
		Range result = Range.shift(r1, delta,true);
		Range expected = new Range(1,2);
		
		assertEquals("Shifting range by 2 with zero crossing allowed",expected,result);
	}
	
	@Test
	public void shiftWithZeroCrossingFalse() {
		Range r1 = new Range(-1,0);
		double delta = 2;
		
		Range result = Range.shift(r1, delta,false);
		Range expected = new Range(0,2);
		
		//lower bound will not be 1 as it is not allowed to go over 0
		
		assertEquals("Shifting range by 2 with zero crossing not allowed",expected,result);
	}
	
	
	@Test
	public void shiftWithNegativeDelta() {
		Range r1 = new Range(-1,0);
		double delta = -1;
		
		Range result = Range.shift(r1, delta,true);
		Range expected = new Range(-2,-1);
		
		
		assertEquals("Shifting range by -1 ",expected,result);
	}
	
	@Test
	public void shiftWithZeroDelta() {
		Range r1 = new Range(-1,0);
		double delta = 0;
		
		Range result = Range.shift(r1, delta,true);
		Range expected = new Range(-1,0);
		
		
		assertEquals("Shifting range by 0 ",expected,result);
	}
	
	@After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }
	
}
