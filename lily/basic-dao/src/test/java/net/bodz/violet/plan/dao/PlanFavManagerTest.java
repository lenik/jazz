package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanFav;
import net.bodz.violet.plan.PlanFavSamples;

public class PlanFavManagerTest
        extends AbstractManagerTest<PlanFav, PlanFavMapper, PlanFavManager> {

    @Override
    public PlanFav buildSample()
            throws Exception {
        PlanFavSamples a = new PlanFavSamples();
        return a.buildWired(tables);
    }

}
