package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractTableTest<TestQuestion, TestQuestionMask, TestQuestionMapper> {

    @Override
    public TestQuestion buildSample() {
        Course course = tables.pickAny(CourseMapper.class, "course");
        return TestQuestionSamples.build(course);
    }

}
