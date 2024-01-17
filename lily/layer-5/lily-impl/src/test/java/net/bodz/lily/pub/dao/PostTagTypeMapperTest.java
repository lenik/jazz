package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTagType;
import net.bodz.lily.pub.PostTagTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTagTypeMapperTest
        extends AbstractTableTest<PostTagType, PostTagTypeMapper> {

    @Override
    public PostTagType buildSample()
            throws Exception {
        PostTagTypeSamples a = new PostTagTypeSamples();
        return a.buildWired(tables);
    }

}
