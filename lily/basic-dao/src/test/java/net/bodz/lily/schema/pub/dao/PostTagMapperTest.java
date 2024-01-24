package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostTag;
import net.bodz.lily.schema.pub.PostTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTagMapperTest
        extends AbstractTableTest<PostTag, PostTagMapper> {

    @Override
    public PostTag buildSample()
            throws Exception {
        PostTagSamples a = new PostTagSamples();
        return a.buildWired(tables);
    }

}
