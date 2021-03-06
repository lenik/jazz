package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoSamples;

public class PlanDoMapperTest
        extends AbstractMapperTest<PlanDo, PlanDoMask, PlanDoMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanDo buildSample() {
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        PlanDo parent = tables.pickAny(PlanDoMapper.class, "plando");
        return PlanDoSamples.build(plan, parent);
    }

}
