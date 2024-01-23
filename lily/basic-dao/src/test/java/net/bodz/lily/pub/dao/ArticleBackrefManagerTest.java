package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleBackref;
import net.bodz.lily.pub.ArticleBackrefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleBackrefManagerTest
        extends AbstractManagerTest<ArticleBackref, ArticleBackrefMapper, ArticleBackrefManager> {

    @Override
    public ArticleBackref buildSample()
            throws Exception {
        ArticleBackrefSamples a = new ArticleBackrefSamples();
        return a.buildWired(tables);
    }

}
