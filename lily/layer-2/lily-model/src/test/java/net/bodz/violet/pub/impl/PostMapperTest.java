package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.Post;
import net.bodz.violet.pub.PostCategory;
import net.bodz.violet.pub.PostSamples;

public class PostMapperTest
        extends AbstractMapperTest<Post, PostMask, PostMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Post buildSample() {
        PostCategory category = tables.pickAny(PostCategoryMapper.class, "postcat");
        Post parent = tables.pickAny(PostMapper.class, "post");
        return PostSamples.build(category, parent);
    }

}
