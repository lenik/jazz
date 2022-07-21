package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostBackref;
import net.bodz.lily.pub.PostBackrefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostBackrefMapperTest
        extends AbstractMapperTest<PostBackref, PostBackrefMask, PostBackrefMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostBackref buildSample() {
        return PostBackrefSamples.build();
    }

}
