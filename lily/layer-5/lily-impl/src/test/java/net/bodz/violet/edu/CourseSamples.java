package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class CourseSamples
        extends TestSamples {

    public static Course build(CourseCategory category) {
        Course a = new Course();
        a.setLabel("course-1");
        a.setDescription("A course named course-1.");
        a.setCategory(category);
        return a;
    }

}
