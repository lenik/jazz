package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleParameterType;
import net.bodz.lily.schema.pub.ArticleParameterTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleParameterTypeManagerTest
        extends AbstractManagerTest<ArticleParameterType, ArticleParameterTypeMapper, ArticleParameterTypeManager> {

    @Override
    public ArticleParameterType buildSample()
            throws Exception {
        ArticleParameterTypeSamples a = new ArticleParameterTypeSamples();
        return a.buildWired(tables);
    }

}
