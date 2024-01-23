package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleCategorySamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleCategoryManagerTest
        extends AbstractManagerTest<ArticleCategory, ArticleCategoryMapper, ArticleCategoryManager> {

    @Override
    public ArticleCategory buildSample()
            throws Exception {
        ArticleCategorySamples a = new ArticleCategorySamples();
        return a.buildWired(tables);
    }

}
