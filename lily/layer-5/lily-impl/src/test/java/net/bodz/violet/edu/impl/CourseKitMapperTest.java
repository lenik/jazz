package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseKit;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitSamples;

public class CourseKitMapperTest
        extends AbstractTableTest<CourseKit, CourseKitMask, CourseKitMapper> {

    @Override
    public CourseKit buildSample() {
        CourseKitCategory category = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        Course course = tables.pickAny(CourseMapper.class, "course");
        return CourseKitSamples.build(category, course);
    }

}
