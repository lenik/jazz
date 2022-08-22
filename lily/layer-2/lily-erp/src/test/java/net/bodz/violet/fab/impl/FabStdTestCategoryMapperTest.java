package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTestCategory;
import net.bodz.violet.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryMapperTest
        extends AbstractTableTest<FabStdTestCategory, FabStdTestCategoryMask, FabStdTestCategoryMapper> {

    @Override
    public FabStdTestCategory buildSample() {
        return FabStdTestCategorySamples.build();
    }

}
