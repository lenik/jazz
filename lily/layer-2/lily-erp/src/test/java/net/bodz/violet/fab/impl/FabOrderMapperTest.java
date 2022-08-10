package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabOrder;
import net.bodz.violet.fab.FabOrderSamples;

public class FabOrderMapperTest
        extends AbstractMapperTest<FabOrder, FabOrderMask, FabOrderMapper> {

    @Override
    public FabOrder buildSample() {
        return FabOrderSamples.build();
    }

}
