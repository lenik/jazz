package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperSamples;

public class TestPaperMapperTest
        extends AbstractTableTest<TestPaper, TestPaperMask, TestPaperMapper> {

    @Override
    public TestPaper buildSample() {
        Course course = tables.pickAny(CourseMapper.class, "course");
        return TestPaperSamples.build(course);
    }

}
