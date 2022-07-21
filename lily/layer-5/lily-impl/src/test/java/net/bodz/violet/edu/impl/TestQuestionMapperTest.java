package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractMapperTest<TestQuestion, TestQuestionMask, TestQuestionMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestQuestion buildSample() {
        Course course = tables.pickAny(CourseMapper.class, "course");
        return TestQuestionSamples.build(course);
    }

}
