package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanSamples;

public class PlanMapperTest
        extends AbstractMapperTest<Plan, PlanMask, PlanMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Plan buildSample() {
        return PlanSamples.build();
    }

}
