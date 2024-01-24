package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanParameter;
import net.bodz.violet.schema.plan.PlanParameterSamples;

public class PlanParameterMapperTest
        extends AbstractTableTest<PlanParameter, PlanParameterMapper> {

    @Override
    public PlanParameter buildSample()
            throws Exception {
        PlanParameterSamples a = new PlanParameterSamples();
        return a.buildWired(tables);
    }

}
