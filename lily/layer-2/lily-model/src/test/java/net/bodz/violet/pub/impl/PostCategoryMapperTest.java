package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostCategory;
import net.bodz.violet.pub.PostCategorySamples;

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
