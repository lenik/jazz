package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleCategorySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class ArticleCategoryMapperTest
        extends AbstractMapperTest<ArticleCategory, ArticleCategoryMask, ArticleCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArticleCategory buildSample() {
        return ArticleCategorySamples.build();
    }

}
