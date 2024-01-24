package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleParameter;
import net.bodz.lily.schema.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleParameterManagerTest
        extends AbstractManagerTest<ArticleParameter, ArticleParameterMapper, ArticleParameterManager> {

    @Override
    public ArticleParameter buildSample()
            throws Exception {
        ArticleParameterSamples a = new ArticleParameterSamples();
        return a.buildWired(tables);
    }

}
