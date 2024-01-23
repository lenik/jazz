package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryReviewVote;
import net.bodz.violet.plan.DiaryReviewVoteSamples;

public class DiaryReviewVoteManagerTest
        extends AbstractManagerTest<DiaryReviewVote, DiaryReviewVoteMapper, DiaryReviewVoteManager> {

    @Override
    public DiaryReviewVote buildSample()
            throws Exception {
        DiaryReviewVoteSamples a = new DiaryReviewVoteSamples();
        return a.buildWired(tables);
    }

}
