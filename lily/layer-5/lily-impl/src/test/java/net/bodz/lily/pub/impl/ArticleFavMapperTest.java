package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleFav;
import net.bodz.lily.pub.ArticleFavSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleFavMapperTest
        extends AbstractMapperTest<ArticleFav, ArticleFavMask, ArticleFavMapper> {

    @Override
    public ArticleFav buildSample() {
        return ArticleFavSamples.build();
    }

}
