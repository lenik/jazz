package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleParameter;
import net.bodz.lily.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class ArticleParameterMapperTest
        extends AbstractMapperTest<ArticleParameter, ArticleParameterMask, ArticleParameterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArticleParameter buildSample() {
        return ArticleParameterSamples.build();
    }

}
