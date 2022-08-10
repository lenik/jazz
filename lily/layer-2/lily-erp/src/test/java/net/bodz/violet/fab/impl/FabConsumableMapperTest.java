package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabConsumable;
import net.bodz.violet.fab.FabConsumableSamples;

public class FabConsumableMapperTest
        extends AbstractMapperTest<FabConsumable, FabConsumableMask, FabConsumableMapper> {

    @Override
    public FabConsumable buildSample() {
        return FabConsumableSamples.build();
    }

}
