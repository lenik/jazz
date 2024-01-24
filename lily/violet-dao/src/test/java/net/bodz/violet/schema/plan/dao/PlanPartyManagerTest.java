package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanParty;
import net.bodz.violet.schema.plan.PlanPartySamples;

public class PlanPartyManagerTest
        extends AbstractManagerTest<PlanParty, PlanPartyMapper, PlanPartyManager> {

    @Override
    public PlanParty buildSample()
            throws Exception {
        PlanPartySamples a = new PlanPartySamples();
        return a.buildWired(tables);
    }

}
