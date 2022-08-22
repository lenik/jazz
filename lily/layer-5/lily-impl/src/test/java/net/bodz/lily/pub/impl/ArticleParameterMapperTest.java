package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleParameter;
import net.bodz.lily.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleParameterMapperTest
        extends AbstractTableTest<ArticleParameter, ArticleParameterMask, ArticleParameterMapper> {

    @Override
    public ArticleParameter buildSample() {
        return ArticleParameterSamples.build();
    }

}
