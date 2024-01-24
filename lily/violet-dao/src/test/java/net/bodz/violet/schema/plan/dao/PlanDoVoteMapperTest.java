package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanDoVote;
import net.bodz.violet.schema.plan.PlanDoVoteSamples;

public class PlanDoVoteMapperTest
        extends AbstractTableTest<PlanDoVote, PlanDoVoteMapper> {

    @Override
    public PlanDoVote buildSample()
            throws Exception {
        PlanDoVoteSamples a = new PlanDoVoteSamples();
        return a.buildWired(tables);
    }

}
