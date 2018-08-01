package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.PlanVote;
import net.bodz.violet.plan.PlanVoteSamples;

public class PlanVoteMapperTest
        extends AbstractMapperTest<PlanVote, PlanVoteMask, PlanVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanVote buildSample() {
        return PlanVoteSamples.build();
    }

}
