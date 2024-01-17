package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostFav;
import net.bodz.lily.pub.PostFavSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostFavManagerTest
        extends AbstractManagerTest<PostFav, PostFavMapper, PostFavManager> {

    @Override
    public PostFav buildSample()
            throws Exception {
        PostFavSamples a = new PostFavSamples();
        return a.buildWired(tables);
    }

}
