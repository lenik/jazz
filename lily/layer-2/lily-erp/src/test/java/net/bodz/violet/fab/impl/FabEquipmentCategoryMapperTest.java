package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabEquipmentCategory;
import net.bodz.violet.fab.FabEquipmentCategorySamples;

public class FabEquipmentCategoryMapperTest
        extends AbstractMapperTest<FabEquipmentCategory, FabEquipmentCategoryMask, FabEquipmentCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabEquipmentCategory buildSample() {
        return FabEquipmentCategorySamples.build();
    }

}
