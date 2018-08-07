package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.ArticleTalkVote;
import net.bodz.lily.pub.ArticleTalkVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleTalkVoteMapperTest
        extends AbstractMapperTest<ArticleTalkVote, ArticleTalkVoteMask, ArticleTalkVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleTalkVote buildSample() {
        return ArticleTalkVoteSamples.build();
    }

}
