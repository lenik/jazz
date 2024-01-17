package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleVoteMapperTest
        extends AbstractTableTest<ArticleVote, ArticleVoteMapper> {

    @Override
    public ArticleVote buildSample()
            throws Exception {
        ArticleVoteSamples a = new ArticleVoteSamples();
        return a.buildWired(tables);
    }

}
