package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanPhase;
import net.bodz.violet.plan.PlanSamples;

public class PlanMapperTest
        extends AbstractTableTest<Plan, PlanMask, PlanMapper> {

    @Override
    public Plan buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        PlanCategory category = tables.pickAny(PlanCategoryMapper.class, "plancat");
        PlanPhase phase = tables.pickAny(PlanPhaseMapper.class, "planphase");
        return PlanSamples.build(op, category, phase);
    }

}
