package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleFav;
import net.bodz.lily.schema.pub.ArticleFavSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleFavManagerTest
        extends AbstractManagerTest<ArticleFav, ArticleFavMapper, ArticleFavManager> {

    @Override
    public ArticleFav buildSample()
            throws Exception {
        ArticleFavSamples a = new ArticleFavSamples();
        return a.buildWired(tables);
    }

}
