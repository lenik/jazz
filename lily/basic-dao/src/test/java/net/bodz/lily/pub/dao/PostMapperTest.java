package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.Post;
import net.bodz.lily.pub.PostSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostMapperTest
        extends AbstractTableTest<Post, PostMapper> {

    @Override
    public Post buildSample()
            throws Exception {
        PostSamples a = new PostSamples();
        return a.buildWired(tables);
    }

}
