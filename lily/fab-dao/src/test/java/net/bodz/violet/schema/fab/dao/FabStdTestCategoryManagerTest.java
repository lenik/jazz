package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdTestCategory;
import net.bodz.violet.schema.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryManagerTest
        extends AbstractManagerTest<FabStdTestCategory, FabStdTestCategoryMapper, FabStdTestCategoryManager> {

    @Override
    public FabStdTestCategory buildSample()
            throws Exception {
        FabStdTestCategorySamples a = new FabStdTestCategorySamples();
        return a.buildWired(tables);
    }

}
