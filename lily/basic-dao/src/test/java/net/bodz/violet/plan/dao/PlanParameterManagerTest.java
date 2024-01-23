package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanParameter;
import net.bodz.violet.plan.PlanParameterSamples;

public class PlanParameterManagerTest
        extends AbstractManagerTest<PlanParameter, PlanParameterMapper, PlanParameterManager> {

    @Override
    public PlanParameter buildSample()
            throws Exception {
        PlanParameterSamples a = new PlanParameterSamples();
        return a.buildWired(tables);
    }

}
