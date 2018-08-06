package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractMapperTest<CourseFav, CourseFavMask, CourseFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CourseFav buildSample() {
        return CourseFavSamples.build();
    }

}
