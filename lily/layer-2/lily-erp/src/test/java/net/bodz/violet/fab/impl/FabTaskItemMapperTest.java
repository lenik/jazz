package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabTaskItem;
import net.bodz.violet.fab.FabTaskItemSamples;

public class FabTaskItemMapperTest
        extends AbstractMapperTest<FabTaskItem, FabTaskItemMask, FabTaskItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabTaskItem buildSample() {
        return FabTaskItemSamples.build();
    }

}
