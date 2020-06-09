package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabFnConsumable;
import net.bodz.violet.fab.FabFnConsumableSamples;

public class FabFnConsumableMapperTest
        extends AbstractMapperTest<FabFnConsumable, FabFnConsumableMask, FabFnConsumableMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabFnConsumable buildSample() {
        return FabFnConsumableSamples.build();
    }

}
