package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanDoParameter;
import net.bodz.violet.schema.plan.PlanDoParameterSamples;

public class PlanDoParameterMapperTest
        extends AbstractTableTest<PlanDoParameter, PlanDoParameterMapper> {

    @Override
    public PlanDoParameter buildSample()
            throws Exception {
        PlanDoParameterSamples a = new PlanDoParameterSamples();
        return a.buildWired(tables);
    }

}
