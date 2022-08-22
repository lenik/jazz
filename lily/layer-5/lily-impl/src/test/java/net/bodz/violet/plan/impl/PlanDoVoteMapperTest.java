package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoVote;
import net.bodz.violet.plan.PlanDoVoteSamples;

public class PlanDoVoteMapperTest
        extends AbstractTableTest<PlanDoVote, PlanDoVoteMask, PlanDoVoteMapper> {

    @Override
    public PlanDoVote buildSample() {
        PlanDo planDo = tables.pickAny(PlanDoMapper.class, "plando");
        User user = tables.pickAny(UserMapper.class, "user");
        return PlanDoVoteSamples.build(planDo, user);
    }

}
