package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleParameter;
import net.bodz.lily.schema.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleParameterMapperTest
        extends AbstractTableTest<ArticleParameter, ArticleParameterMapper> {

    @Override
    public ArticleParameter buildSample()
            throws Exception {
        ArticleParameterSamples a = new ArticleParameterSamples();
        return a.buildWired(tables);
    }

}
