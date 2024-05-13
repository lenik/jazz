package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanDoParameterType;
import net.bodz.violet.schema.plan.PlanDoParameterTypeSamples;

public class PlanDoParameterTypeMapperTest
        extends AbstractTableTest<PlanDoParameterType, PlanDoParameterTypeMapper> {

    @Override
    public PlanDoParameterType buildSample()
            throws Exception {
        PlanDoParameterTypeSamples a = new PlanDoParameterTypeSamples();
        return a.buildWired(tables);
    }

}
