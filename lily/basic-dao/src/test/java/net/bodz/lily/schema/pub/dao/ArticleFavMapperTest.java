package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleFav;
import net.bodz.lily.schema.pub.ArticleFavSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleFavMapperTest
        extends AbstractTableTest<ArticleFav, ArticleFavMapper> {

    @Override
    public ArticleFav buildSample()
            throws Exception {
        ArticleFavSamples a = new ArticleFavSamples();
        return a.buildWired(tables);
    }

}
