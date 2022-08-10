package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabStdTestCategory;
import net.bodz.violet.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryMapperTest
        extends AbstractMapperTest<FabStdTestCategory, FabStdTestCategoryMask, FabStdTestCategoryMapper> {

    @Override
    public FabStdTestCategory buildSample() {
        return FabStdTestCategorySamples.build();
    }

}
