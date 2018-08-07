package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuFnConsumable;
import net.bodz.violet.manu.ManuFnConsumableSamples;

public class ManuFnConsumableMapperTest
        extends AbstractMapperTest<ManuFnConsumable, ManuFnConsumableMask, ManuFnConsumableMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuFnConsumable buildSample() {
        return ManuFnConsumableSamples.build();
    }

}
