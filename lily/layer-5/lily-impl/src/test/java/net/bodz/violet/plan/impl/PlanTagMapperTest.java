package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.plan.PlanTag;
import net.bodz.violet.plan.PlanTagSamples;

public class PlanTagMapperTest
        extends AbstractMapperTest<PlanTag, PlanTagMask, PlanTagMapper> {

    @Override
    public PlanTag buildSample() {
        return PlanTagSamples.build();
    }

}
