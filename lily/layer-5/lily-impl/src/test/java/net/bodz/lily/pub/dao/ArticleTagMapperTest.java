package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTag;
import net.bodz.lily.pub.ArticleTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTagMapperTest
        extends AbstractTableTest<ArticleTag, ArticleTagMask, ArticleTagMapper> {

    @Override
    public ArticleTag buildSample()
            throws Exception {
        ArticleTagSamples a = new ArticleTagSamples();
        a.tag = tables.pickAny(ArticleTagTypeMapper.class, "articletag");
        a.article = tables.pickAny(ArticleMapper.class, "article");
        return a.build();
    }

}
