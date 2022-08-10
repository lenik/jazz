package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabTaskItem;
import net.bodz.violet.fab.FabTaskItemSamples;

public class FabTaskItemMapperTest
        extends AbstractMapperTest<FabTaskItem, FabTaskItemMask, FabTaskItemMapper> {

    @Override
    public FabTaskItem buildSample() {
        return FabTaskItemSamples.build();
    }

}
