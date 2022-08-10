package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractMapperTest<PlanCategory, PlanCategoryMask, PlanCategoryMapper> {

    @Override
    public PlanCategory buildSample() {
        return PlanCategorySamples.build();
    }

}
