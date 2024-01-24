package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.Plan;
import net.bodz.violet.schema.plan.PlanSamples;

public class PlanMapperTest
        extends AbstractTableTest<Plan, PlanMapper> {

    @Override
    public Plan buildSample()
            throws Exception {
        PlanSamples a = new PlanSamples();
        return a.buildWired(tables);
    }

}
