package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabEquipment;
import net.bodz.violet.fab.FabEquipmentSamples;

public class FabEquipmentMapperTest
        extends AbstractTableTest<FabEquipment, FabEquipmentMask, FabEquipmentMapper> {

    @Override
    public FabEquipment buildSample() {
        return FabEquipmentSamples.build();
    }

}
