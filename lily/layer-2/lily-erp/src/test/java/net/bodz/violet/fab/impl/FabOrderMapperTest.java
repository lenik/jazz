package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabOrder;
import net.bodz.violet.fab.FabOrderSamples;

public class FabOrderMapperTest
        extends AbstractMapperTest<FabOrder, FabOrderMask, FabOrderMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabOrder buildSample() {
        return FabOrderSamples.build();
    }

}
