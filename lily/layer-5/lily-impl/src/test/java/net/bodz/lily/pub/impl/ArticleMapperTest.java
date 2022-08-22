package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.Article;
import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleMapperTest
        extends AbstractTableTest<Article, ArticleMask, ArticleMapper> {

    @Override
    public Article buildSample() {
        ArticleCategory category = tables.pickAny(ArticleCategoryMapper.class, "articlecat");
        return ArticleSamples.build(category);
    }

}
