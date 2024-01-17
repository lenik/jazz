package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryReviewVote;
import net.bodz.violet.plan.DiaryReviewVoteSamples;

public class DiaryReviewVoteMapperTest
        extends AbstractTableTest<DiaryReviewVote, DiaryReviewVoteMapper> {

    @Override
    public DiaryReviewVote buildSample()
            throws Exception {
        DiaryReviewVoteSamples a = new DiaryReviewVoteSamples();
        return a.buildWired(tables);
    }

}
