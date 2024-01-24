package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleTalk;
import net.bodz.lily.schema.pub.ArticleTalkSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTalkMapperTest
        extends AbstractTableTest<ArticleTalk, ArticleTalkMapper> {

    @Override
    public ArticleTalk buildSample()
            throws Exception {
        ArticleTalkSamples a = new ArticleTalkSamples();
        return a.buildWired(tables);
    }

}
