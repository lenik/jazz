package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseCategory;
import net.bodz.violet.edu.CourseSamples;

public class CourseMapperTest
        extends AbstractTableTest<Course, CourseMask, CourseMapper> {

    @Override
    public Course buildSample() {
        CourseCategory category = tables.pickAny(CourseCategoryMapper.class, "coursecat");
        return CourseSamples.build(category);
    }

}
