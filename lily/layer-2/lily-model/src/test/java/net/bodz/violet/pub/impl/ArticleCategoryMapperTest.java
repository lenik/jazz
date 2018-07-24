package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.ArticleCategory;

public class ArticleCategoryMapperTest
        extends AbstractMapperTest<ArticleCategory, ArticleCategoryMask, ArticleCategoryMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public ArticleCategory buildSample() {
        return ArticleCategorySamples.build();
    }

}
