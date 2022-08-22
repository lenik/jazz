package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoParameter;
import net.bodz.violet.plan.PlanDoParameterSamples;

public class PlanDoParameterMapperTest
        extends AbstractTableTest<PlanDoParameter, PlanDoParameterMask, PlanDoParameterMapper> {

    @Override
    public PlanDoParameter buildSample() {
        return PlanDoParameterSamples.build();
    }

}
