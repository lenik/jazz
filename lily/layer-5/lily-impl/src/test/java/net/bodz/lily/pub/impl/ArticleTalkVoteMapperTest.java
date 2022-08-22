package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleTalkVote;
import net.bodz.lily.pub.ArticleTalkVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTalkVoteMapperTest
        extends AbstractTableTest<ArticleTalkVote, ArticleTalkVoteMask, ArticleTalkVoteMapper> {

    @Override
    public ArticleTalkVote buildSample() {
        return ArticleTalkVoteSamples.build();
    }

}
