package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleCategory;
import net.bodz.violet.pub.ArticleCategorySamples;

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
