package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.issue.IssueFav;
import net.bodz.violet.issue.IssueFavSamples;

public class IssueFavMapperTest
        extends AbstractMapperTest<IssueFav, IssueFavMask, IssueFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public IssueFav buildSample() {
        return IssueFavSamples.build();
    }

}
