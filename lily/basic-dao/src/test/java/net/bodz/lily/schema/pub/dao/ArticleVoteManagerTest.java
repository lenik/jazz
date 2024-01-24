package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.ArticleVote;
import net.bodz.lily.schema.pub.ArticleVoteSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ArticleVoteManagerTest
        extends AbstractManagerTest<ArticleVote, ArticleVoteMapper, ArticleVoteManager> {

    @Override
    public ArticleVote buildSample()
            throws Exception {
        ArticleVoteSamples a = new ArticleVoteSamples();
        return a.buildWired(tables);
    }

}
