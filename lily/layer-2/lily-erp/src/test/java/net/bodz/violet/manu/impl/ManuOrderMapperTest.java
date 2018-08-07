package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuOrder;
import net.bodz.violet.manu.ManuOrderSamples;

public class ManuOrderMapperTest
        extends AbstractMapperTest<ManuOrder, ManuOrderMask, ManuOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuOrder buildSample() {
        return ManuOrderSamples.build();
    }

}
