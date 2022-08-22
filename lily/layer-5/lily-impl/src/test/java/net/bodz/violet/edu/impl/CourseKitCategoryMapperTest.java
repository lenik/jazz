package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitCategorySamples;

public class CourseKitCategoryMapperTest
        extends AbstractTableTest<CourseKitCategory, CourseKitCategoryMask, CourseKitCategoryMapper> {

    @Override
    public CourseKitCategory buildSample() {
        CourseKitCategory parent = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        return CourseKitCategorySamples.build(parent);
    }

}
