package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.ArticleTalk;

public class ArticleTalkMapperTest
        extends AbstractMapperTest<ArticleTalk, ArticleTalkMask, ArticleTalkMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public ArticleTalk buildSample() {
        return ArticleTalkSamples.build();
    }

}
