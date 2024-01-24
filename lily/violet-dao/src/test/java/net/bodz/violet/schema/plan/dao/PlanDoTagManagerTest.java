package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanDoTag;
import net.bodz.violet.schema.plan.PlanDoTagSamples;

public class PlanDoTagManagerTest
        extends AbstractManagerTest<PlanDoTag, PlanDoTagMapper, PlanDoTagManager> {

    @Override
    public PlanDoTag buildSample()
            throws Exception {
        PlanDoTagSamples a = new PlanDoTagSamples();
        return a.buildWired(tables);
    }

}
