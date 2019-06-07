package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class CourseKitSamples
        extends TestSamples {

    public static CourseKit build(CourseKitCategory category, Course course) {
        CourseKit a = new CourseKit();
        a.setLabel("courseKit-1");
        a.setDescription("A courseKit named courseKit-1.");
        a.setCategory(category);
        a.setCourse(course);
        return a;
    }

}
