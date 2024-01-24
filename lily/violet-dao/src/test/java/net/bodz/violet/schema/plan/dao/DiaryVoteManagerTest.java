package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryVote;
import net.bodz.violet.schema.plan.DiaryVoteSamples;

public class DiaryVoteManagerTest
        extends AbstractManagerTest<DiaryVote, DiaryVoteMapper, DiaryVoteManager> {

    @Override
    public DiaryVote buildSample()
            throws Exception {
        DiaryVoteSamples a = new DiaryVoteSamples();
        return a.buildWired(tables);
    }

}
