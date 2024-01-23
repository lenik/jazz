package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryManagerTest
        extends AbstractManagerTest<PlanCategory, PlanCategoryMapper, PlanCategoryManager> {

    @Override
    public PlanCategory buildSample()
            throws Exception {
        PlanCategorySamples a = new PlanCategorySamples();
        return a.buildWired(tables);
    }

}
