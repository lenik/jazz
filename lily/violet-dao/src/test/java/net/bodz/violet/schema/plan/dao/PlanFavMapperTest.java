package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanFav;
import net.bodz.violet.schema.plan.PlanFavSamples;

public class PlanFavMapperTest
        extends AbstractTableTest<PlanFav, PlanFavMapper> {

    @Override
    public PlanFav buildSample()
            throws Exception {
        PlanFavSamples a = new PlanFavSamples();
        return a.buildWired(tables);
    }

}
