package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostTag;
import net.bodz.lily.pub.PostTagSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class PostTagMapperTest
        extends AbstractMapperTest<PostTag, PostTagMask, PostTagMapper> {

    @Override
    public PostTag buildSample() {
        return PostTagSamples.build();
    }

}
