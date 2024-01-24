package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdTest;
import net.bodz.violet.schema.fab.FabStdTestSamples;

public class FabStdTestMapperTest
        extends AbstractTableTest<FabStdTest, FabStdTestMapper> {

    @Override
    public FabStdTest buildSample()
            throws Exception {
        FabStdTestSamples a = new FabStdTestSamples();
        return a.buildWired(tables);
    }

}
