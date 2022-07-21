package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitCategorySamples;

public class CourseKitCategoryMapperTest
        extends AbstractMapperTest<CourseKitCategory, CourseKitCategoryMask, CourseKitCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CourseKitCategory buildSample() {
        CourseKitCategory parent = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        return CourseKitCategorySamples.build(parent);
    }

}
