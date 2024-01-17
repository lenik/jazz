package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleFav;
import net.bodz.lily.pub.ArticleFavSamples;
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
