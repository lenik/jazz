package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleVoteMapperTest
        extends AbstractMapperTest<ArticleVote, ArticleVoteMask, ArticleVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleVote buildSample() {
        return ArticleVoteSamples.build();
    }

}
