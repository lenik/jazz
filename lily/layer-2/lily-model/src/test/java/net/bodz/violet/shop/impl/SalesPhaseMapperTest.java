package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractMapperTest<SalesPhase, SalesPhaseMask, SalesPhaseMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public SalesPhase buildSample() {
        return SalesPhaseSamples.build();
    }

}
