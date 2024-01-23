package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoParameter;
import net.bodz.violet.plan.PlanDoParameterSamples;

public class PlanDoParameterMapperTest
        extends AbstractTableTest<PlanDoParameter, PlanDoParameterMapper> {

    @Override
    public PlanDoParameter buildSample()
            throws Exception {
        PlanDoParameterSamples a = new PlanDoParameterSamples();
        return a.buildWired(tables);
    }

}
