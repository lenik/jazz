package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractTableTest<PlanCategory, PlanCategoryMask, PlanCategoryMapper> {

    @Override
    public PlanCategory buildSample() {
        return PlanCategorySamples.build();
    }

}
