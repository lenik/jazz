package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuEquipmentCategory;
import net.bodz.violet.manu.ManuEquipmentCategorySamples;

public class ManuEquipmentCategoryMapperTest
        extends AbstractMapperTest<ManuEquipmentCategory, ManuEquipmentCategoryMask, ManuEquipmentCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuEquipmentCategory buildSample() {
        return ManuEquipmentCategorySamples.build();
    }

}
