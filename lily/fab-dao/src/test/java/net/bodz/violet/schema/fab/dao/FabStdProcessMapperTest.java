package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdProcess;
import net.bodz.violet.schema.fab.FabStdProcessSamples;

public class FabStdProcessMapperTest
        extends AbstractTableTest<FabStdProcess, FabStdProcessMapper> {

    @Override
    public FabStdProcess buildSample()
            throws Exception {
        FabStdProcessSamples a = new FabStdProcessSamples();
        return a.buildWired(tables);
    }

}
