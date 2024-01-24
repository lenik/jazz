package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanDo;
import net.bodz.violet.schema.plan.PlanDoSamples;

public class PlanDoMapperTest
        extends AbstractTableTest<PlanDo, PlanDoMapper> {

    @Override
    public PlanDo buildSample()
            throws Exception {
        PlanDoSamples a = new PlanDoSamples();
        return a.buildWired(tables);
    }

}
