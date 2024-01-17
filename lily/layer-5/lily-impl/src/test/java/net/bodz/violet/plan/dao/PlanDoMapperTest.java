package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoSamples;

public class PlanDoMapperTest
        extends AbstractTableTest<PlanDo, PlanDoMapper> {

    @Override
    public PlanDo buildSample()
            throws Exception {
        PlanDoSamples a = new PlanDoSamples();
        return a.buildWired(tables);
    }

}
