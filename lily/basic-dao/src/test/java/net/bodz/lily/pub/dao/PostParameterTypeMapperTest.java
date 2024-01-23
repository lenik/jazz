package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostParameterType;
import net.bodz.lily.pub.PostParameterTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostParameterTypeMapperTest
        extends AbstractTableTest<PostParameterType, PostParameterTypeMapper> {

    @Override
    public PostParameterType buildSample()
            throws Exception {
        PostParameterTypeSamples a = new PostParameterTypeSamples();
        return a.buildWired(tables);
    }

}
