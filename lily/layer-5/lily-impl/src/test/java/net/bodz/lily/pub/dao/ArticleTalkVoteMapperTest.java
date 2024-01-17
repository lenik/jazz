package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTalkVote;
import net.bodz.lily.pub.ArticleTalkVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTalkVoteMapperTest
        extends AbstractTableTest<ArticleTalkVote, ArticleTalkVoteMapper> {

    @Override
    public ArticleTalkVote buildSample()
            throws Exception {
        ArticleTalkVoteSamples a = new ArticleTalkVoteSamples();
        return a.buildWired(tables);
    }

}
