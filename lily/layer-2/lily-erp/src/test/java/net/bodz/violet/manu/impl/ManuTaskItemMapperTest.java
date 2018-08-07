package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTaskItem;
import net.bodz.violet.manu.ManuTaskItemSamples;

public class ManuTaskItemMapperTest
        extends AbstractMapperTest<ManuTaskItem, ManuTaskItemMask, ManuTaskItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTaskItem buildSample() {
        return ManuTaskItemSamples.build();
    }

}
