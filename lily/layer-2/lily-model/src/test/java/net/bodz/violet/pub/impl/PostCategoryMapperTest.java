package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.PostCategory;

public class PostCategoryMapperTest
        extends AbstractMapperTest<PostCategory, PostCategoryMask, PostCategoryMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public PostCategory buildSample() {
        return PostCategorySamples.build();
    }

}
