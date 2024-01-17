package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTag;
import net.bodz.lily.pub.PostTagSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostTagManagerTest
        extends AbstractManagerTest<PostTag, PostTagMapper, PostTagManager> {

    @Override
    public PostTag buildSample()
            throws Exception {
        PostTagSamples a = new PostTagSamples();
        return a.buildWired(tables);
    }

}
