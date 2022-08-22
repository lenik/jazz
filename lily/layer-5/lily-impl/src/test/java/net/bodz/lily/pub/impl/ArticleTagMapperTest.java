package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleTag;
import net.bodz.lily.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTagMapperTest
        extends AbstractTableTest<ArticleTag, ArticleTagMask, ArticleTagMapper> {

    @Override
    public ArticleTag buildSample() {
        return ArticleTagSamples.build();
    }

}
