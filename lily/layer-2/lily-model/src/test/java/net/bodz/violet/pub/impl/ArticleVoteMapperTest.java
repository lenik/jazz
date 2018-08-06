package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleVote;
import net.bodz.violet.pub.ArticleVoteSamples;

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
