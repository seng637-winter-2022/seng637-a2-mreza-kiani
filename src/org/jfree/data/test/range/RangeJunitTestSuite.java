package org.jfree.data.test.range;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CombineIgnoringNaNMethodTest.class,
        IntersectMethodTest.class,
        ExpandToIncludeTest.class,
        isNaNRangeTest.class,
        ShiftTest.class
})
public class RangeJunitTestSuite {
}
