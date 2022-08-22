package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanFav;
import net.bodz.violet.plan.PlanFavSamples;

public class PlanFavMapperTest
        extends AbstractTableTest<PlanFav, PlanFavMask, PlanFavMapper> {

    @Override
    public PlanFav buildSample() {
        return PlanFavSamples.build();
    }

}
