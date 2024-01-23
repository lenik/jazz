package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanParty;
import net.bodz.violet.plan.PlanPartySamples;

public class PlanPartyMapperTest
        extends AbstractTableTest<PlanParty, PlanPartyMapper> {

    @Override
    public PlanParty buildSample()
            throws Exception {
        PlanPartySamples a = new PlanPartySamples();
        return a.buildWired(tables);
    }

}
