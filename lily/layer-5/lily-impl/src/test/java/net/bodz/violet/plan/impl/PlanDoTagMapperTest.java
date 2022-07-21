package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.PlanDoTag;
import net.bodz.violet.plan.PlanDoTagSamples;

public class PlanDoTagMapperTest
        extends AbstractMapperTest<PlanDoTag, PlanDoTagMask, PlanDoTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PlanDoTag buildSample() {
        return PlanDoTagSamples.build();
    }

}
