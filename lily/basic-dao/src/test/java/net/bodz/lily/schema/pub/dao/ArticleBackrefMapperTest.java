package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleBackref;
import net.bodz.lily.schema.pub.ArticleBackrefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleBackrefMapperTest
        extends AbstractTableTest<ArticleBackref, ArticleBackrefMapper> {

    @Override
    public ArticleBackref buildSample()
            throws Exception {
        ArticleBackrefSamples a = new ArticleBackrefSamples();
        return a.buildWired(tables);
    }

}
