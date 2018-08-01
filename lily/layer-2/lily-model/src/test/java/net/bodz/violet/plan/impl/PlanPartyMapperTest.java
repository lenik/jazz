package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.PlanParty;
import net.bodz.violet.plan.PlanPartySamples;

public class PlanPartyMapperTest
        extends AbstractMapperTest<PlanParty, PlanPartyMask, PlanPartyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanParty buildSample() {
        return PlanPartySamples.build();
    }

}
