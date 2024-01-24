package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.Plan;
import net.bodz.violet.schema.plan.PlanSamples;

public class PlanManagerTest
        extends AbstractManagerTest<Plan, PlanMapper, PlanManager> {

    @Override
    public Plan buildSample()
            throws Exception {
        PlanSamples a = new PlanSamples();
        return a.buildWired(tables);
    }

}
