package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleVoteMapperTest
        extends AbstractTableTest<ArticleVote, ArticleVoteMask, ArticleVoteMapper> {

    @Override
    public ArticleVote buildSample() {
        return ArticleVoteSamples.build();
    }

}
