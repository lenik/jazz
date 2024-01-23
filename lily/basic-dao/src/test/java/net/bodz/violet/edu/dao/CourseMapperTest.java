package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseSamples;

public class CourseMapperTest
        extends AbstractTableTest<Course, CourseMapper> {

    @Override
    public Course buildSample()
            throws Exception {
        CourseSamples a = new CourseSamples();
        return a.buildWired(tables);
    }

}
