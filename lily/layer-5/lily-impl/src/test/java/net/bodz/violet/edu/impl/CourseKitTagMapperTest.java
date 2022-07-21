package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.CourseKitTag;
import net.bodz.violet.edu.CourseKitTagSamples;

public class CourseKitTagMapperTest
        extends AbstractMapperTest<CourseKitTag, CourseKitTagMask, CourseKitTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CourseKitTag buildSample() {
        return CourseKitTagSamples.build();
    }

}
