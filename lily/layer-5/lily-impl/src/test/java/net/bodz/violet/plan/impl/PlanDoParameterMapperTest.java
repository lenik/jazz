package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.PlanDoParameter;
import net.bodz.violet.plan.PlanDoParameterSamples;

public class PlanDoParameterMapperTest
        extends AbstractMapperTest<PlanDoParameter, PlanDoParameterMask, PlanDoParameterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PlanDoParameter buildSample() {
        return PlanDoParameterSamples.build();
    }

}
