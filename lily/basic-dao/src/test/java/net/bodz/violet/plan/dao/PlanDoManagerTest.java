package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoSamples;

public class PlanDoManagerTest
        extends AbstractManagerTest<PlanDo, PlanDoMapper, PlanDoManager> {

    @Override
    public PlanDo buildSample()
            throws Exception {
        PlanDoSamples a = new PlanDoSamples();
        return a.buildWired(tables);
    }

}
