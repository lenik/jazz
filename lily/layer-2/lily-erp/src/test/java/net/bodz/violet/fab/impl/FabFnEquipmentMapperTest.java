package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabFnEquipment;
import net.bodz.violet.fab.FabFnEquipmentSamples;

public class FabFnEquipmentMapperTest
        extends AbstractMapperTest<FabFnEquipment, FabFnEquipmentMask, FabFnEquipmentMapper> {

    @Override
    public FabFnEquipment buildSample() {
        return FabFnEquipmentSamples.build();
    }

}
