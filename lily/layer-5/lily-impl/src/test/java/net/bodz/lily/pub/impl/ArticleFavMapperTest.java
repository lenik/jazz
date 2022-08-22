package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleFav;
import net.bodz.lily.pub.ArticleFavSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleFavMapperTest
        extends AbstractTableTest<ArticleFav, ArticleFavMask, ArticleFavMapper> {

    @Override
    public ArticleFav buildSample() {
        return ArticleFavSamples.build();
    }

}
