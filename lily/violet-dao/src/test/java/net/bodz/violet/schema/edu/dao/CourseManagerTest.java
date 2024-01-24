package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.Course;
import net.bodz.violet.schema.edu.CourseSamples;

public class CourseManagerTest
        extends AbstractManagerTest<Course, CourseMapper, CourseManager> {

    @Override
    public Course buildSample()
            throws Exception {
        CourseSamples a = new CourseSamples();
        return a.buildWired(tables);
    }

}
