package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.Post;
import net.bodz.lily.pub.PostSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostManagerTest
        extends AbstractManagerTest<Post, PostMapper, PostManager> {

    @Override
    public Post buildSample()
            throws Exception {
        PostSamples a = new PostSamples();
        return a.buildWired(tables);
    }

}
