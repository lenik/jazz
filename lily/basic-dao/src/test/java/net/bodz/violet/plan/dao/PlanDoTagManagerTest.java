package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanDoTag;
import net.bodz.violet.plan.PlanDoTagSamples;

public class PlanDoTagManagerTest
        extends AbstractManagerTest<PlanDoTag, PlanDoTagMapper, PlanDoTagManager> {

    @Override
    public PlanDoTag buildSample()
            throws Exception {
        PlanDoTagSamples a = new PlanDoTagSamples();
        return a.buildWired(tables);
    }

}
