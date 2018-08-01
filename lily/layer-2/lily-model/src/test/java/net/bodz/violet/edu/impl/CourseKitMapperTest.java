package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.CourseKit;
import net.bodz.violet.edu.CourseKitSamples;

public class CourseKitMapperTest
        extends AbstractMapperTest<CourseKit, CourseKitMask, CourseKitMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CourseKit buildSample() {
        return CourseKitSamples.build();
    }

}
