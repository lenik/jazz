package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseKit;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitSamples;

public class CourseKitMapperTest
        extends AbstractMapperTest<CourseKit, CourseKitMask, CourseKitMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CourseKit buildSample() {
        CourseKitCategory category = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        Course course = tables.pickAny(CourseMapper.class, "course");
        return CourseKitSamples.build(category, course);
    }

}
