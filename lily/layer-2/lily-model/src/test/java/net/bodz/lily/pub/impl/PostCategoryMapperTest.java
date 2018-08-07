package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostCategory;
import net.bodz.lily.pub.PostCategorySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PostCategoryMapperTest
        extends AbstractMapperTest<PostCategory, PostCategoryMask, PostCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostCategory buildSample() {
        return PostCategorySamples.build();
    }

}
