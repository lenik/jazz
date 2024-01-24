package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdTestCategory;
import net.bodz.violet.schema.fab.FabStdTestCategorySamples;

public class FabStdTestCategoryMapperTest
        extends AbstractTableTest<FabStdTestCategory, FabStdTestCategoryMapper> {

    @Override
    public FabStdTestCategory buildSample()
            throws Exception {
        FabStdTestCategorySamples a = new FabStdTestCategorySamples();
        return a.buildWired(tables);
    }

}
