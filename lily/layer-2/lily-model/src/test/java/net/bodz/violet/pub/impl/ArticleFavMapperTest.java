package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleFav;
import net.bodz.violet.pub.ArticleFavSamples;

public class ArticleFavMapperTest
        extends AbstractMapperTest<ArticleFav, ArticleFavMask, ArticleFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleFav buildSample() {
        return ArticleFavSamples.build();
    }

}
