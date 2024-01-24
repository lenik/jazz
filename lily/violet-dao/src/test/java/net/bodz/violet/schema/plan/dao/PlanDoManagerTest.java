package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanDo;
import net.bodz.violet.schema.plan.PlanDoSamples;

public class PlanDoManagerTest
        extends AbstractManagerTest<PlanDo, PlanDoMapper, PlanDoManager> {

    @Override
    public PlanDo buildSample()
            throws Exception {
        PlanDoSamples a = new PlanDoSamples();
        return a.buildWired(tables);
    }

}
