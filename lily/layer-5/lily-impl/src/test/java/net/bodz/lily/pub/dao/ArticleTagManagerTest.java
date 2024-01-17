package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTag;
import net.bodz.lily.pub.ArticleTagSamples;
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
