package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostTag;
import net.bodz.violet.pub.PostTagSamples;

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
