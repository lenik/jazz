package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanParty;
import net.bodz.violet.schema.plan.PlanPartySamples;

public class PlanPartyMapperTest
        extends AbstractTableTest<PlanParty, PlanPartyMapper> {

    @Override
    public PlanParty buildSample()
            throws Exception {
        PlanPartySamples a = new PlanPartySamples();
        return a.buildWired(tables);
    }

}
