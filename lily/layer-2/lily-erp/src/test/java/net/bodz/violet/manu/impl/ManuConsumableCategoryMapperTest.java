package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuConsumableCategory;
import net.bodz.violet.manu.ManuConsumableCategorySamples;

public class ManuConsumableCategoryMapperTest
        extends AbstractMapperTest<ManuConsumableCategory, ManuConsumableCategoryMask, ManuConsumableCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuConsumableCategory buildSample() {
        return ManuConsumableCategorySamples.build();
    }

}
