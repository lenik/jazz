package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleFav;
import net.bodz.lily.pub.ArticleFavSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class ArticleFavMapperTest
        extends AbstractMapperTest<ArticleFav, ArticleFavMask, ArticleFavMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArticleFav buildSample() {
        return ArticleFavSamples.build();
    }

}
