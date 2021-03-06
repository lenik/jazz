package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostTag;
import net.bodz.lily.pub.PostTagSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PostTagMapperTest
        extends AbstractMapperTest<PostTag, PostTagMask, PostTagMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostTag buildSample() {
        return PostTagSamples.build();
    }

}
