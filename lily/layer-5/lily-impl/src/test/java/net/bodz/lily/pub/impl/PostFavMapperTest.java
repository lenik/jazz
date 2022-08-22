package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostFav;
import net.bodz.lily.pub.PostFavSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostFavMapperTest
        extends AbstractTableTest<PostFav, PostFavMask, PostFavMapper> {

    @Override
    public PostFav buildSample() {
        return PostFavSamples.build();
    }

}
