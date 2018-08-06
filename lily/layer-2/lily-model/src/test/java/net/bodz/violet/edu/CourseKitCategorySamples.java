package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class CourseKitCategorySamples
        extends TestSamples {

    public static CourseKitCategory build(CourseKitCategory parent) {
        CourseKitCategory a = new CourseKitCategory();
        a.setLabel("courseKitCategory-1");
        a.setDescription("A courseKitCategory named courseKitCategory-1.");
        a.setParent(parent);
        return a;
    }

}
