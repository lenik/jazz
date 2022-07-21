package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostFav;
import net.bodz.lily.pub.PostFavSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostFavMapperTest
        extends AbstractMapperTest<PostFav, PostFavMask, PostFavMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostFav buildSample() {
        return PostFavSamples.build();
    }

}
