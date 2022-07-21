package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractMapperTest<SalesPhase, SalesPhaseMask, SalesPhaseMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public SalesPhase buildSample() {
        return SalesPhaseSamples.build();
    }

}
