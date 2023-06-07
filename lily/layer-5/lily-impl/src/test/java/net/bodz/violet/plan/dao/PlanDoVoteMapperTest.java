package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoVote;
import net.bodz.violet.plan.PlanDoVoteSamples;

public class PlanDoVoteMapperTest
        extends AbstractTableTest<PlanDoVote, PlanDoVoteMask, PlanDoVoteMapper> {

    @Override
    public PlanDoVote buildSample()
            throws Exception {
        PlanDoVoteSamples a = new PlanDoVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(PlanDoMapper.class, "plando");
        return a.build();
    }

}
