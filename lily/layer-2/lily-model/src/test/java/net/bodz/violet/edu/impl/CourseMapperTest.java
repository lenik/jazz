package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseSamples;

public class CourseMapperTest
        extends AbstractMapperTest<Course, CourseMask, CourseMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Course buildSample() {
        return CourseSamples.build();
    }

}
