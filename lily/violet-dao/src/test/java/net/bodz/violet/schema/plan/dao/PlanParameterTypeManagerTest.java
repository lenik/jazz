package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanParameterType;
import net.bodz.violet.schema.plan.PlanParameterTypeSamples;

public class PlanParameterTypeManagerTest
        extends AbstractManagerTest<PlanParameterType, PlanParameterTypeMapper, PlanParameterTypeManager> {

    @Override
    public PlanParameterType buildSample()
            throws Exception {
        PlanParameterTypeSamples a = new PlanParameterTypeSamples();
        return a.buildWired(tables);
    }

}
