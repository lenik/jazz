package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleTag;
import net.bodz.violet.pub.ArticleTagSamples;

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
