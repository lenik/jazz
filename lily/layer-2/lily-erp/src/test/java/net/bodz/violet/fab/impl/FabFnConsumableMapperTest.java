package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabFnConsumable;
import net.bodz.violet.fab.FabFnConsumableSamples;

public class FabFnConsumableMapperTest
        extends AbstractMapperTest<FabFnConsumable, FabFnConsumableMask, FabFnConsumableMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabFnConsumable buildSample() {
        return FabFnConsumableSamples.build();
    }

}
