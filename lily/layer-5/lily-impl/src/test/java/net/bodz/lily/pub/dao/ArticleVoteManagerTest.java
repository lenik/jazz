package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
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
