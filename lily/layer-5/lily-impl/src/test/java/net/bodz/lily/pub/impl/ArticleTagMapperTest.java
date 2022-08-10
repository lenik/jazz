package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleTag;
import net.bodz.lily.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleTagMapperTest
        extends AbstractMapperTest<ArticleTag, ArticleTagMask, ArticleTagMapper> {

    @Override
    public ArticleTag buildSample() {
        return ArticleTagSamples.build();
    }

}
