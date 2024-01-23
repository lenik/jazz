package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanSamples;

public class PlanManagerTest
        extends AbstractManagerTest<Plan, PlanMapper, PlanManager> {

    @Override
    public Plan buildSample()
            throws Exception {
        PlanSamples a = new PlanSamples();
        return a.buildWired(tables);
    }

}
