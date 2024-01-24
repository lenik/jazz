package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleTagType;
import net.bodz.lily.schema.pub.ArticleTagTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTagTypeMapperTest
        extends AbstractTableTest<ArticleTagType, ArticleTagTypeMapper> {

    @Override
    public ArticleTagType buildSample()
            throws Exception {
        ArticleTagTypeSamples a = new ArticleTagTypeSamples();
        return a.buildWired(tables);
    }

}
