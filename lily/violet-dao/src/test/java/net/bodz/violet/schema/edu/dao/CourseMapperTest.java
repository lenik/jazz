package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.Course;
import net.bodz.violet.schema.edu.CourseSamples;

public class CourseMapperTest
        extends AbstractTableTest<Course, CourseMapper> {

    @Override
    public Course buildSample()
            throws Exception {
        CourseSamples a = new CourseSamples();
        return a.buildWired(tables);
    }

}
