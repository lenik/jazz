package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostTag;
import net.bodz.lily.pub.PostTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTagMapperTest
        extends AbstractTableTest<PostTag, PostTagMask, PostTagMapper> {

    @Override
    public PostTag buildSample() {
        return PostTagSamples.build();
    }

}
