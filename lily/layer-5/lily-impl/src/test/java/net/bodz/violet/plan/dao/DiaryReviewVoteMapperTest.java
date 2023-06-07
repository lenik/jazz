package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryReviewVote;
import net.bodz.violet.plan.DiaryReviewVoteSamples;

public class DiaryReviewVoteMapperTest
        extends AbstractTableTest<DiaryReviewVote, DiaryReviewVoteMask, DiaryReviewVoteMapper> {

    @Override
    public DiaryReviewVote buildSample()
            throws Exception {
        DiaryReviewVoteSamples a = new DiaryReviewVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(DiaryReviewMapper.class, "diaryrev");
        return a.build();
    }

}
