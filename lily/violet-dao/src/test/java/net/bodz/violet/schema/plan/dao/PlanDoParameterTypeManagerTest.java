package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanDoParameterType;
import net.bodz.violet.schema.plan.PlanDoParameterTypeSamples;

public class PlanDoParameterTypeManagerTest
        extends AbstractManagerTest<PlanDoParameterType, PlanDoParameterTypeMapper, PlanDoParameterTypeManager> {

    @Override
    public PlanDoParameterType buildSample()
            throws Exception {
        PlanDoParameterTypeSamples a = new PlanDoParameterTypeSamples();
        return a.buildWired(tables);
    }

}
