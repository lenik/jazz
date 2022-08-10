package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleBackref;
import net.bodz.lily.pub.ArticleBackrefSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleBackrefMapperTest
        extends AbstractMapperTest<ArticleBackref, ArticleBackrefMask, ArticleBackrefMapper> {

    @Override
    public ArticleBackref buildSample() {
        return ArticleBackrefSamples.build();
    }

}
