package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuEquipment;
import net.bodz.violet.manu.ManuEquipmentSamples;

public class ManuEquipmentMapperTest
        extends AbstractMapperTest<ManuEquipment, ManuEquipmentMask, ManuEquipmentMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuEquipment buildSample() {
        return ManuEquipmentSamples.build();
    }

}
