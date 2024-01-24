package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostParameterType;
import net.bodz.lily.schema.pub.PostParameterTypeSamples;
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
