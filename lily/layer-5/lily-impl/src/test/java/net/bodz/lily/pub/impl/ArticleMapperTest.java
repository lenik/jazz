package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.Article;
import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleMapperTest
        extends AbstractMapperTest<Article, ArticleMask, ArticleMapper> {

    @Override
    public Article buildSample() {
        ArticleCategory category = tables.pickAny(ArticleCategoryMapper.class, "articlecat");
        return ArticleSamples.build(category);
    }

}
