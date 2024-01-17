package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostParameterType;
import net.bodz.lily.pub.PostParameterTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostParameterTypeManagerTest
        extends AbstractManagerTest<PostParameterType, PostParameterTypeMapper, PostParameterTypeManager> {

    @Override
    public PostParameterType buildSample()
            throws Exception {
        PostParameterTypeSamples a = new PostParameterTypeSamples();
        return a.buildWired(tables);
    }

}
