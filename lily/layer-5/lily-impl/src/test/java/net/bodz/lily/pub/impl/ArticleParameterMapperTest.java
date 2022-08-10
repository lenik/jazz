package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleParameter;
import net.bodz.lily.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleParameterMapperTest
        extends AbstractMapperTest<ArticleParameter, ArticleParameterMask, ArticleParameterMapper> {

    @Override
    public ArticleParameter buildSample() {
        return ArticleParameterSamples.build();
    }

}
