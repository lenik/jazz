package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuFnEquipment;
import net.bodz.violet.manu.ManuFnEquipmentSamples;

public class ManuFnEquipmentMapperTest
        extends AbstractMapperTest<ManuFnEquipment, ManuFnEquipmentMask, ManuFnEquipmentMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuFnEquipment buildSample() {
        return ManuFnEquipmentSamples.build();
    }

}
