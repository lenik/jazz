package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleParameterType;
import net.bodz.lily.pub.ArticleParameterTypeSamples;
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
