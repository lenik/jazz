package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabConsumable;
import net.bodz.violet.fab.FabConsumableSamples;

public class FabConsumableMapperTest
        extends AbstractTableTest<FabConsumable, FabConsumableMask, FabConsumableMapper> {

    @Override
    public FabConsumable buildSample() {
        return FabConsumableSamples.build();
    }

}
