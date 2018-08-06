package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.ArticleTalkVote;
import net.bodz.violet.pub.ArticleTalkVoteSamples;

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
