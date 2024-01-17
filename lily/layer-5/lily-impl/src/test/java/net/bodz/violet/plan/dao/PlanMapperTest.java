package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanSamples;

public class PlanMapperTest
        extends AbstractTableTest<Plan, PlanMapper> {

    @Override
    public Plan buildSample()
            throws Exception {
        PlanSamples a = new PlanSamples();
        return a.buildWired(tables);
    }

}
