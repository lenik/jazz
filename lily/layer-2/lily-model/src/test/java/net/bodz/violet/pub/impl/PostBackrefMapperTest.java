package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostBackref;
import net.bodz.violet.pub.PostBackrefSamples;

public class PostBackrefMapperTest
        extends AbstractMapperTest<PostBackref, PostBackrefMask, PostBackrefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostBackref buildSample() {
        return PostBackrefSamples.build();
    }

}
