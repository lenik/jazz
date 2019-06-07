package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleBackref;
import net.bodz.lily.pub.ArticleBackrefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleBackrefMapperTest
        extends AbstractMapperTest<ArticleBackref, ArticleBackrefMask, ArticleBackrefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleBackref buildSample() {
        return ArticleBackrefSamples.build();
    }

}
