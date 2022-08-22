package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanVote;
import net.bodz.violet.plan.PlanVoteSamples;

public class PlanVoteMapperTest
        extends AbstractTableTest<PlanVote, PlanVoteMask, PlanVoteMapper> {

    @Override
    public PlanVote buildSample() {
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        User user = tables.pickAny(UserMapper.class, "user");
        return PlanVoteSamples.build(plan, user);
    }

}
