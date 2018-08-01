package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleTalk;
import net.bodz.violet.pub.ArticleTalkSamples;

public class ArticleTalkMapperTest
        extends AbstractMapperTest<ArticleTalk, ArticleTalkMask, ArticleTalkMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleTalk buildSample() {
        return ArticleTalkSamples.build();
    }

}
