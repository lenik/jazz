package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanCategory;
import net.bodz.violet.schema.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractTableTest<PlanCategory, PlanCategoryMapper> {

    @Override
    public PlanCategory buildSample()
            throws Exception {
        PlanCategorySamples a = new PlanCategorySamples();
        return a.buildWired(tables);
    }

}
