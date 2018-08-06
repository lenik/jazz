package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.DiaryReview;
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
        DiaryReview review = tables.pickAny(DiaryReviewMapper.class, "diaryrev");
        User user = tables.pickAny(UserMapper.class, "user");
        return DiaryReviewVoteSamples.build(review, user);
    }

}
