package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanDoVote;
import net.bodz.violet.schema.plan.PlanDoVoteSamples;

public class PlanDoVoteManagerTest
        extends AbstractManagerTest<PlanDoVote, PlanDoVoteMapper, PlanDoVoteManager> {

    @Override
    public PlanDoVote buildSample()
            throws Exception {
        PlanDoVoteSamples a = new PlanDoVoteSamples();
        return a.buildWired(tables);
    }

}
