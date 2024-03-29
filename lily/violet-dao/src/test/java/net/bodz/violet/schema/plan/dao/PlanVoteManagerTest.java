package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanVote;
import net.bodz.violet.schema.plan.PlanVoteSamples;

public class PlanVoteManagerTest
        extends AbstractManagerTest<PlanVote, PlanVoteMapper, PlanVoteManager> {

    @Override
    public PlanVote buildSample()
            throws Exception {
        PlanVoteSamples a = new PlanVoteSamples();
        return a.buildWired(tables);
    }

}
