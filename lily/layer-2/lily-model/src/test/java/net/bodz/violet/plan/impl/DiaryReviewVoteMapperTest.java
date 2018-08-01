package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.DiaryReviewVote;
import net.bodz.violet.plan.DiaryReviewVoteSamples;

public class DiaryReviewVoteMapperTest
        extends AbstractMapperTest<DiaryReviewVote, DiaryReviewVoteMask, DiaryReviewVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryReviewVote buildSample() {
        return DiaryReviewVoteSamples.build();
    }

}
