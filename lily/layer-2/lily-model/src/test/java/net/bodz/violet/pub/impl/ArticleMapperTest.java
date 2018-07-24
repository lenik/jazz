package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.Article;

public class ArticleMapperTest
        extends AbstractMapperTest<Article, ArticleMask, ArticleMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public Article buildSample() {
        return ArticleSamples.build();
    }

}
