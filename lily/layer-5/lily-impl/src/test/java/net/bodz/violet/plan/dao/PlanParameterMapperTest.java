package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanParameter;
import net.bodz.violet.plan.PlanParameterSamples;

public class PlanParameterMapperTest
        extends AbstractTableTest<PlanParameter, PlanParameterMapper> {

    @Override
    public PlanParameter buildSample()
            throws Exception {
        PlanParameterSamples a = new PlanParameterSamples();
        return a.buildWired(tables);
    }

}
