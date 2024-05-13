package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanParameterType;
import net.bodz.violet.schema.plan.PlanParameterTypeSamples;

public class PlanParameterTypeMapperTest
        extends AbstractTableTest<PlanParameterType, PlanParameterTypeMapper> {

    @Override
    public PlanParameterType buildSample()
            throws Exception {
        PlanParameterTypeSamples a = new PlanParameterTypeSamples();
        return a.buildWired(tables);
    }

}
