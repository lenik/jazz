package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.Article;
import net.bodz.lily.schema.pub.ArticleSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleMapperTest
        extends AbstractTableTest<Article, ArticleMapper> {

    @Override
    public Article buildSample()
            throws Exception {
        ArticleSamples a = new ArticleSamples();
        return a.buildWired(tables);
    }

}
