package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanFav;
import net.bodz.violet.plan.PlanFavSamples;

public class PlanFavMapperTest
        extends AbstractTableTest<PlanFav, PlanFavMapper> {

    @Override
    public PlanFav buildSample()
            throws Exception {
        PlanFavSamples a = new PlanFavSamples();
        return a.buildWired(tables);
    }

}
