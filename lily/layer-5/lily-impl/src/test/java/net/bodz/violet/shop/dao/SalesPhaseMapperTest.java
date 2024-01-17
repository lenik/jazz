package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractTableTest<SalesPhase, SalesPhaseMapper> {

    @Override
    public SalesPhase buildSample()
            throws Exception {
        SalesPhaseSamples a = new SalesPhaseSamples();
        return a.buildWired(tables);
    }

}
