package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanPhase;
import net.bodz.violet.plan.PlanPhaseSamples;

public class PlanPhaseMapperTest
        extends AbstractTableTest<PlanPhase, PlanPhaseMask, PlanPhaseMapper> {

    @Override
    public PlanPhase buildSample()
            throws Exception {
        PlanPhaseSamples a = new PlanPhaseSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
