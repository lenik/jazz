package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleBackref;
import net.bodz.violet.pub.ArticleBackrefSamples;

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
