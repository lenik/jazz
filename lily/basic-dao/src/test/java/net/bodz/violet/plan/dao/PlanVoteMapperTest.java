package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanVote;
import net.bodz.violet.plan.PlanVoteSamples;

public class PlanVoteMapperTest
        extends AbstractTableTest<PlanVote, PlanVoteMapper> {

    @Override
    public PlanVote buildSample()
            throws Exception {
        PlanVoteSamples a = new PlanVoteSamples();
        return a.buildWired(tables);
    }

}
