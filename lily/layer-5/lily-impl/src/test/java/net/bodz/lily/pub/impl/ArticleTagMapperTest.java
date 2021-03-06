package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleTag;
import net.bodz.lily.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleTagMapperTest
        extends AbstractMapperTest<ArticleTag, ArticleTagMask, ArticleTagMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleTag buildSample() {
        return ArticleTagSamples.build();
    }

}
