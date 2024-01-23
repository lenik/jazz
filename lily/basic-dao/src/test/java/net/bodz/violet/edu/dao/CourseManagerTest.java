package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseSamples;

public class CourseManagerTest
        extends AbstractManagerTest<Course, CourseMapper, CourseManager> {

    @Override
    public Course buildSample()
            throws Exception {
        CourseSamples a = new CourseSamples();
        return a.buildWired(tables);
    }

}
