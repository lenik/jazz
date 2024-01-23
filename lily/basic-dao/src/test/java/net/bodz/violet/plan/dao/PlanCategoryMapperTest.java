package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractTableTest<PlanCategory, PlanCategoryMapper> {

    @Override
    public PlanCategory buildSample()
            throws Exception {
        PlanCategorySamples a = new PlanCategorySamples();
        return a.buildWired(tables);
    }

}
