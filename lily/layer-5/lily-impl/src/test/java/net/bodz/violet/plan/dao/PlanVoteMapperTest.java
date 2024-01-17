package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanVote;
import net.bodz.violet.plan.PlanVoteSamples;

public class PlanVoteMapperTest
        extends AbstractTableTest<PlanVote, PlanVoteCriteriaBuilder, PlanVoteMapper> {

    @Override
    public PlanVote buildSample()
            throws Exception {
        PlanVoteSamples a = new PlanVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(PlanMapper.class, "plan");
        return a.build();
    }

}
