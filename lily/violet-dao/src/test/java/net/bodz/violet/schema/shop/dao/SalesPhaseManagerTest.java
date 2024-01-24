package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.shop.SalesPhase;
import net.bodz.violet.schema.shop.SalesPhaseSamples;

public class SalesPhaseManagerTest
        extends AbstractManagerTest<SalesPhase, SalesPhaseMapper, SalesPhaseManager> {

    @Override
    public SalesPhase buildSample()
            throws Exception {
        SalesPhaseSamples a = new SalesPhaseSamples();
        return a.buildWired(tables);
    }

}
