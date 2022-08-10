package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class ArticleVoteMapperTest
        extends AbstractMapperTest<ArticleVote, ArticleVoteMask, ArticleVoteMapper> {

    @Override
    public ArticleVote buildSample() {
        return ArticleVoteSamples.build();
    }

}
