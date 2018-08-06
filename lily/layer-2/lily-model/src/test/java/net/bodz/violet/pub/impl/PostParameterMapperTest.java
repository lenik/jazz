package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostParameter;
import net.bodz.violet.pub.PostParameterSamples;

public class PostParameterMapperTest
        extends AbstractMapperTest<PostParameter, PostParameterMask, PostParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostParameter buildSample() {
        return PostParameterSamples.build();
    }

}
