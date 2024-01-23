package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.Article;
import net.bodz.lily.pub.ArticleSamples;
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
