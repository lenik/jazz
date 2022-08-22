package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostParameter;
import net.bodz.lily.pub.PostParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostParameterMapperTest
        extends AbstractTableTest<PostParameter, PostParameterMask, PostParameterMapper> {

    @Override
    public PostParameter buildSample() {
        return PostParameterSamples.build();
    }

}
