package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabFnConsumable;
import net.bodz.violet.fab.FabFnConsumableSamples;

public class FabFnConsumableMapperTest
        extends AbstractTableTest<FabFnConsumable, FabFnConsumableMask, FabFnConsumableMapper> {

    @Override
    public FabFnConsumable buildSample() {
        return FabFnConsumableSamples.build();
    }

}
