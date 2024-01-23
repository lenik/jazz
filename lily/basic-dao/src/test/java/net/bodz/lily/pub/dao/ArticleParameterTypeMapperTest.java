package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleParameterType;
import net.bodz.lily.pub.ArticleParameterTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleParameterTypeMapperTest
        extends AbstractTableTest<ArticleParameterType, ArticleParameterTypeMapper> {

    @Override
    public ArticleParameterType buildSample()
            throws Exception {
        ArticleParameterTypeSamples a = new ArticleParameterTypeSamples();
        return a.buildWired(tables);
    }

}
