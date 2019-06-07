package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.PlanParameter;
import net.bodz.violet.plan.PlanParameterSamples;

public class PlanParameterMapperTest
        extends AbstractMapperTest<PlanParameter, PlanParameterMask, PlanParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanParameter buildSample() {
        return PlanParameterSamples.build();
    }

}
