package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostParameter;
import net.bodz.lily.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostParameterMapperTest
        extends AbstractTableTest<PostParameter, PostParameterMask, PostParameterMapper> {

    @Override
    public PostParameter buildSample()
            throws Exception {
        PostParameterSamples a = new PostParameterSamples();
        a.post = tables.pickAny(PostMapper.class, "post");
        a.parameter = tables.pickAny(PostParameterTypeMapper.class, "postparm");
        return a.build();
    }

}
