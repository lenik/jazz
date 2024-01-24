package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanParameter;
import net.bodz.violet.schema.plan.PlanParameterSamples;

public class PlanParameterManagerTest
        extends AbstractManagerTest<PlanParameter, PlanParameterMapper, PlanParameterManager> {

    @Override
    public PlanParameter buildSample()
            throws Exception {
        PlanParameterSamples a = new PlanParameterSamples();
        return a.buildWired(tables);
    }

}
