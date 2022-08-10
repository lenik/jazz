package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractMapperTest<SalesPhase, SalesPhaseMask, SalesPhaseMapper> {

    @Override
    public SalesPhase buildSample() {
        return SalesPhaseSamples.build();
    }

}
