package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleCategory;
import net.bodz.lily.schema.pub.ArticleCategorySamples;
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
