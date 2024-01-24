package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanCategory;
import net.bodz.violet.schema.plan.PlanCategorySamples;

public class PlanCategoryManagerTest
        extends AbstractManagerTest<PlanCategory, PlanCategoryMapper, PlanCategoryManager> {

    @Override
    public PlanCategory buildSample()
            throws Exception {
        PlanCategorySamples a = new PlanCategorySamples();
        return a.buildWired(tables);
    }

}
