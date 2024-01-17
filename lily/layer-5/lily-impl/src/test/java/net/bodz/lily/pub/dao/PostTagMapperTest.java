package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTag;
import net.bodz.lily.pub.PostTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTagMapperTest
        extends AbstractTableTest<PostTag, PostTagCriteriaBuilder, PostTagMapper> {

    @Override
    public PostTag buildSample()
            throws Exception {
        PostTagSamples a = new PostTagSamples();
        a.tag = tables.pickAny(PostTagTypeMapper.class, "posttag");
        a.post = tables.pickAny(PostMapper.class, "post");
        return a.build();
    }

}
