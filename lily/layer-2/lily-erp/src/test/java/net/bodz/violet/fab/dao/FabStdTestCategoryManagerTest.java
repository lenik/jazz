package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabStdTestCategory;
import net.bodz.violet.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryManagerTest
        extends AbstractManagerTest<FabStdTestCategory, FabStdTestCategoryMapper, FabStdTestCategoryManager> {

    @Override
    public FabStdTestCategory buildSample()
            throws Exception {
        FabStdTestCategorySamples a = new FabStdTestCategorySamples();
        return a.buildWired(tables);
    }

}
