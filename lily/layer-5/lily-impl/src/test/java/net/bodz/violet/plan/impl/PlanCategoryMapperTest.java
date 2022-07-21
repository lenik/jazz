package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractMapperTest<PlanCategory, PlanCategoryMask, PlanCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PlanCategory buildSample() {
        return PlanCategorySamples.build();
    }

}
