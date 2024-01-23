package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostBackref;
import net.bodz.lily.pub.PostBackrefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostBackrefManagerTest
        extends AbstractManagerTest<PostBackref, PostBackrefMapper, PostBackrefManager> {

    @Override
    public PostBackref buildSample()
            throws Exception {
        PostBackrefSamples a = new PostBackrefSamples();
        return a.buildWired(tables);
    }

}
