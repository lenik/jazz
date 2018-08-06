package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class CourseCategorySamples
        extends TestSamples {

    public static CourseCategory build() {
        CourseCategory a = new CourseCategory();
        a.setLabel("courseCategory-1");
        a.setDescription("A courseCategory named courseCategory-1.");
        return a;
    }

}
