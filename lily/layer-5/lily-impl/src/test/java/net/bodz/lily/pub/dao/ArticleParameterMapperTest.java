package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleParameter;
import net.bodz.lily.pub.ArticleParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleParameterMapperTest
        extends AbstractTableTest<ArticleParameter, ArticleParameterMask, ArticleParameterMapper> {

    @Override
    public ArticleParameter buildSample()
            throws Exception {
        ArticleParameterSamples a = new ArticleParameterSamples();
        a.article = tables.pickAny(ArticleMapper.class, "article");
        a.parameter = tables.pickAny(ArticleParameterTypeMapper.class, "articleparm");
        return a.build();
    }

}
