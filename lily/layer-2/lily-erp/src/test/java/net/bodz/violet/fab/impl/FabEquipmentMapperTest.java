package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabEquipment;
import net.bodz.violet.fab.FabEquipmentSamples;

public class FabEquipmentMapperTest
        extends AbstractMapperTest<FabEquipment, FabEquipmentMask, FabEquipmentMapper> {

    @Override
    public FabEquipment buildSample() {
        return FabEquipmentSamples.build();
    }

}
