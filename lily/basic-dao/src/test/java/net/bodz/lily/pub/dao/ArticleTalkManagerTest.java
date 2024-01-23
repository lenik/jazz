package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTalk;
import net.bodz.lily.pub.ArticleTalkSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleTalkManagerTest
        extends AbstractManagerTest<ArticleTalk, ArticleTalkMapper, ArticleTalkManager> {

    @Override
    public ArticleTalk buildSample()
            throws Exception {
        ArticleTalkSamples a = new ArticleTalkSamples();
        return a.buildWired(tables);
    }

}
