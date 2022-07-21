package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostParameter;
import net.bodz.lily.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostParameterMapperTest
        extends AbstractMapperTest<PostParameter, PostParameterMask, PostParameterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostParameter buildSample() {
        return PostParameterSamples.build();
    }

}
