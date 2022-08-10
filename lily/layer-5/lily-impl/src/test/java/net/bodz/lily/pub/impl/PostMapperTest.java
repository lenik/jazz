package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.Post;
import net.bodz.lily.pub.PostCategory;
import net.bodz.lily.pub.PostSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class PostMapperTest
        extends AbstractMapperTest<Post, PostMask, PostMapper> {

    @Override
    public Post buildSample() {
        PostCategory category = tables.pickAny(PostCategoryMapper.class, "postcat");
        Post parent = tables.pickAny(PostMapper.class, "post");
        return PostSamples.build(category, parent);
    }

}
