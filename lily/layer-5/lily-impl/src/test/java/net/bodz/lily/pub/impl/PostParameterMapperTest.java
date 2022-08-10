package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostParameter;
import net.bodz.lily.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class PostParameterMapperTest
        extends AbstractMapperTest<PostParameter, PostParameterMask, PostParameterMapper> {

    @Override
    public PostParameter buildSample() {
        return PostParameterSamples.build();
    }

}
