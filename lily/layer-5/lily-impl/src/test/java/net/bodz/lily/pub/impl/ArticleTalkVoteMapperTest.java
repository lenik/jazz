package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleTalkVote;
import net.bodz.lily.pub.ArticleTalkVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleTalkVoteMapperTest
        extends AbstractMapperTest<ArticleTalkVote, ArticleTalkVoteMask, ArticleTalkVoteMapper> {

    @Override
    public ArticleTalkVote buildSample() {
        return ArticleTalkVoteSamples.build();
    }

}
