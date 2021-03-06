package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.CourseKitFav;
import net.bodz.violet.edu.CourseKitFavSamples;

public class CourseKitFavMapperTest
        extends AbstractMapperTest<CourseKitFav, CourseKitFavMask, CourseKitFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CourseKitFav buildSample() {
        return CourseKitFavSamples.build();
    }

}
