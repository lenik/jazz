package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleCategorySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleCategoryMapperTest
        extends AbstractMapperTest<ArticleCategory, ArticleCategoryMask, ArticleCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleCategory buildSample() {
        return ArticleCategorySamples.build();
    }

}
