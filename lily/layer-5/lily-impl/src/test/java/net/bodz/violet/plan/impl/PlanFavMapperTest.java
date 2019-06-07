package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.PlanFav;
import net.bodz.violet.plan.PlanFavSamples;

public class PlanFavMapperTest
        extends AbstractMapperTest<PlanFav, PlanFavMask, PlanFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanFav buildSample() {
        return PlanFavSamples.build();
    }

}
