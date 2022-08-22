package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabConsumableCategory;
import net.bodz.violet.fab.FabConsumableCategorySamples;

public class FabConsumableCategoryMapperTest
        extends AbstractTableTest<FabConsumableCategory, FabConsumableCategoryMask, FabConsumableCategoryMapper> {

    @Override
    public FabConsumableCategory buildSample() {
        return FabConsumableCategorySamples.build();
    }

}
