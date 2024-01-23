package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostParameter;
import net.bodz.lily.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostParameterManagerTest
        extends AbstractManagerTest<PostParameter, PostParameterMapper, PostParameterManager> {

    @Override
    public PostParameter buildSample()
            throws Exception {
        PostParameterSamples a = new PostParameterSamples();
        return a.buildWired(tables);
    }

}
