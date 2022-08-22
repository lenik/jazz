package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractTableTest<SalesPhase, SalesPhaseMask, SalesPhaseMapper> {

    @Override
    public SalesPhase buildSample() {
        return SalesPhaseSamples.build();
    }

}
