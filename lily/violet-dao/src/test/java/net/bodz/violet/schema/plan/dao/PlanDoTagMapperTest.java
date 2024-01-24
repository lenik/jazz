package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanDoTag;
import net.bodz.violet.schema.plan.PlanDoTagSamples;

public class PlanDoTagMapperTest
        extends AbstractTableTest<PlanDoTag, PlanDoTagMapper> {

    @Override
    public PlanDoTag buildSample()
            throws Exception {
        PlanDoTagSamples a = new PlanDoTagSamples();
        return a.buildWired(tables);
    }

}
