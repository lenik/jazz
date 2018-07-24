package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.PlanTag;
import net.bodz.violet.plan.PlanTagSamples;

public class PlanTagMapperTest
        extends AbstractMapperTest<PlanTag, PlanTagMask, PlanTagMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanTag buildSample() {
        return PlanTagSamples.build();
    }

}
