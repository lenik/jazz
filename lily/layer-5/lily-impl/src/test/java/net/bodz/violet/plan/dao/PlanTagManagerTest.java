package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanTag;
import net.bodz.violet.plan.PlanTagSamples;

public class PlanTagManagerTest
        extends AbstractManagerTest<PlanTag, PlanTagMapper, PlanTagManager> {

    @Override
    public PlanTag buildSample()
            throws Exception {
        PlanTagSamples a = new PlanTagSamples();
        return a.buildWired(tables);
    }

}
