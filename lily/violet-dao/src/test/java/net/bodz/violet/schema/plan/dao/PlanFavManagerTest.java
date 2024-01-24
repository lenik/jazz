package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanFav;
import net.bodz.violet.schema.plan.PlanFavSamples;

public class PlanFavManagerTest
        extends AbstractManagerTest<PlanFav, PlanFavMapper, PlanFavManager> {

    @Override
    public PlanFav buildSample()
            throws Exception {
        PlanFavSamples a = new PlanFavSamples();
        return a.buildWired(tables);
    }

}
