package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostFav;
import net.bodz.violet.pub.PostFavSamples;

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
