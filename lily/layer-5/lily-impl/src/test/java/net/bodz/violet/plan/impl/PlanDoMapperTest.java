package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoSamples;

public class PlanDoMapperTest
        extends AbstractTableTest<PlanDo, PlanDoMask, PlanDoMapper> {

    @Override
    public PlanDo buildSample() {
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        PlanDo parent = tables.pickAny(PlanDoMapper.class, "plando");
        return PlanDoSamples.build(plan, parent);
    }

}
