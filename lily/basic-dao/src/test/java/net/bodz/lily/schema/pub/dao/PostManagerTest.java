package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.Post;
import net.bodz.lily.schema.pub.PostSamples;
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
