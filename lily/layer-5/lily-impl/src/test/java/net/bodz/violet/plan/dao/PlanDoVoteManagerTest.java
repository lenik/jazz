package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanDoVote;
import net.bodz.violet.plan.PlanDoVoteSamples;

public class PlanDoVoteManagerTest
        extends AbstractManagerTest<PlanDoVote, PlanDoVoteMapper, PlanDoVoteManager> {

    @Override
    public PlanDoVote buildSample()
            throws Exception {
        PlanDoVoteSamples a = new PlanDoVoteSamples();
        return a.buildWired(tables);
    }

}
