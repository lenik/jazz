package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseManagerTest
        extends AbstractManagerTest<SalesPhase, SalesPhaseMapper, SalesPhaseManager> {

    @Override
    public SalesPhase buildSample()
            throws Exception {
        SalesPhaseSamples a = new SalesPhaseSamples();
        return a.buildWired(tables);
    }

}
