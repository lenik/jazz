package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostBackref;
import net.bodz.lily.pub.PostBackrefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostBackrefMapperTest
        extends AbstractTableTest<PostBackref, PostBackrefMask, PostBackrefMapper> {

    @Override
    public PostBackref buildSample() {
        return PostBackrefSamples.build();
    }

}
