package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleTag;
import net.bodz.lily.schema.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleTagManagerTest
        extends AbstractManagerTest<ArticleTag, ArticleTagMapper, ArticleTagManager> {

    @Override
    public ArticleTag buildSample()
            throws Exception {
        ArticleTagSamples a = new ArticleTagSamples();
        return a.buildWired(tables);
    }

}
