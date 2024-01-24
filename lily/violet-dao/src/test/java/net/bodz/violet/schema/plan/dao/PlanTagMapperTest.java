package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanTag;
import net.bodz.violet.schema.plan.PlanTagSamples;

public class PlanTagMapperTest
        extends AbstractTableTest<PlanTag, PlanTagMapper> {

    @Override
    public PlanTag buildSample()
            throws Exception {
        PlanTagSamples a = new PlanTagSamples();
        return a.buildWired(tables);
    }

}
