package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostParameter;
import net.bodz.lily.schema.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostParameterMapperTest
        extends AbstractTableTest<PostParameter, PostParameterMapper> {

    @Override
    public PostParameter buildSample()
            throws Exception {
        PostParameterSamples a = new PostParameterSamples();
        return a.buildWired(tables);
    }

}
