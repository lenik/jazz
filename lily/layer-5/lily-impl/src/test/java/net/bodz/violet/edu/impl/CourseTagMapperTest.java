package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractMapperTest<CourseTag, CourseTagMask, CourseTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public CourseTag buildSample() {
        return CourseTagSamples.build();
    }

}
