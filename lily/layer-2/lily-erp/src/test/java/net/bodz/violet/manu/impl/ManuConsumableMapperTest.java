package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuConsumable;
import net.bodz.violet.manu.ManuConsumableSamples;

public class ManuConsumableMapperTest
        extends AbstractMapperTest<ManuConsumable, ManuConsumableMask, ManuConsumableMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuConsumable buildSample() {
        return ManuConsumableSamples.build();
    }

}
