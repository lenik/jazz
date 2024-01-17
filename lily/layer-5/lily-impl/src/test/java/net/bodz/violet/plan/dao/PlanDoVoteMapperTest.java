package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoVote;
import net.bodz.violet.plan.PlanDoVoteSamples;

public class PlanDoVoteMapperTest
        extends AbstractTableTest<PlanDoVote, PlanDoVoteMapper> {

    @Override
    public PlanDoVote buildSample()
            throws Exception {
        PlanDoVoteSamples a = new PlanDoVoteSamples();
        return a.buildWired(tables);
    }

}
