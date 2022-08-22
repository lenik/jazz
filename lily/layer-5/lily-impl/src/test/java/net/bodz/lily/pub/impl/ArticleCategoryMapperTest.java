package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleCategoryMapperTest
        extends AbstractTableTest<ArticleCategory, ArticleCategoryMask, ArticleCategoryMapper> {

    @Override
    public ArticleCategory buildSample() {
        return ArticleCategorySamples.build();
    }

}
