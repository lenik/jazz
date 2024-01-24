package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleCategory;
import net.bodz.lily.schema.pub.ArticleCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleCategoryMapperTest
        extends AbstractTableTest<ArticleCategory, ArticleCategoryMapper> {

    @Override
    public ArticleCategory buildSample()
            throws Exception {
        ArticleCategorySamples a = new ArticleCategorySamples();
        return a.buildWired(tables);
    }

}
