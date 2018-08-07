package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostFav;
import net.bodz.lily.pub.PostFavSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PostFavMapperTest
        extends AbstractMapperTest<PostFav, PostFavMask, PostFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostFav buildSample() {
        return PostFavSamples.build();
    }

}
