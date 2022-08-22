package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabOrder;
import net.bodz.violet.fab.FabOrderSamples;

public class FabOrderMapperTest
        extends AbstractTableTest<FabOrder, FabOrderMask, FabOrderMapper> {

    @Override
    public FabOrder buildSample() {
        return FabOrderSamples.build();
    }

}
