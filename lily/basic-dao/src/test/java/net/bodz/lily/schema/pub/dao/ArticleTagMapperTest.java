package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleTag;
import net.bodz.lily.schema.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTagMapperTest
        extends AbstractTableTest<ArticleTag, ArticleTagMapper> {

    @Override
    public ArticleTag buildSample()
            throws Exception {
        ArticleTagSamples a = new ArticleTagSamples();
        return a.buildWired(tables);
    }

}
