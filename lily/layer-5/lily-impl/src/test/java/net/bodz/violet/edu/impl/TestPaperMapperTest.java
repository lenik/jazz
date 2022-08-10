package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperSamples;

public class TestPaperMapperTest
        extends AbstractMapperTest<TestPaper, TestPaperMask, TestPaperMapper> {

    @Override
    public TestPaper buildSample() {
        Course course = tables.pickAny(CourseMapper.class, "course");
        return TestPaperSamples.build(course);
    }

}
