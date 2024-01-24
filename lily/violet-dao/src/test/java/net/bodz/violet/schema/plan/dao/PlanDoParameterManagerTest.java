package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanDoParameter;
import net.bodz.violet.schema.plan.PlanDoParameterSamples;

public class PlanDoParameterManagerTest
        extends AbstractManagerTest<PlanDoParameter, PlanDoParameterMapper, PlanDoParameterManager> {

    @Override
    public PlanDoParameter buildSample()
            throws Exception {
        PlanDoParameterSamples a = new PlanDoParameterSamples();
        return a.buildWired(tables);
    }

}
