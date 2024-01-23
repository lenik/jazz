package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTestCategory;
import net.bodz.violet.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryMapperTest
        extends AbstractTableTest<FabStdTestCategory, FabStdTestCategoryMapper> {

    @Override
    public FabStdTestCategory buildSample()
            throws Exception {
        FabStdTestCategorySamples a = new FabStdTestCategorySamples();
        return a.buildWired(tables);
    }

}
