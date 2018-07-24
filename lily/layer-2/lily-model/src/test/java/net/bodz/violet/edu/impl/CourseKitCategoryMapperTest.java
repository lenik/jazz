package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitCategorySamples;

public class CourseKitCategoryMapperTest
        extends AbstractMapperTest<CourseKitCategory, CourseKitCategoryMask, CourseKitCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CourseKitCategory buildSample() {
        return CourseKitCategorySamples.build();
    }

}
