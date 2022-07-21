package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabFnEquipment;
import net.bodz.violet.fab.FabFnEquipmentSamples;

public class FabFnEquipmentMapperTest
        extends AbstractMapperTest<FabFnEquipment, FabFnEquipmentMask, FabFnEquipmentMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabFnEquipment buildSample() {
        return FabFnEquipmentSamples.build();
    }

}
