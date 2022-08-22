package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanParameter;
import net.bodz.violet.plan.PlanParameterSamples;

public class PlanParameterMapperTest
        extends AbstractTableTest<PlanParameter, PlanParameterMask, PlanParameterMapper> {

    @Override
    public PlanParameter buildSample() {
        return PlanParameterSamples.build();
    }

}
