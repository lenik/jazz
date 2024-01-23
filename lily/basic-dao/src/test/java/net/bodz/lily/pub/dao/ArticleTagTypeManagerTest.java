package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTagType;
import net.bodz.lily.pub.ArticleTagTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleTagTypeManagerTest
        extends AbstractManagerTest<ArticleTagType, ArticleTagTypeMapper, ArticleTagTypeManager> {

    @Override
    public ArticleTagType buildSample()
            throws Exception {
        ArticleTagTypeSamples a = new ArticleTagTypeSamples();
        return a.buildWired(tables);
    }

}
