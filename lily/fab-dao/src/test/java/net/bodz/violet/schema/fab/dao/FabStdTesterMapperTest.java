package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdTester;
import net.bodz.violet.schema.fab.FabStdTesterSamples;

public class FabStdTesterMapperTest
        extends AbstractTableTest<FabStdTester, FabStdTesterMapper> {

    @Override
    public FabStdTester buildSample()
            throws Exception {
        FabStdTesterSamples a = new FabStdTesterSamples();
        return a.buildWired(tables);
    }

}
