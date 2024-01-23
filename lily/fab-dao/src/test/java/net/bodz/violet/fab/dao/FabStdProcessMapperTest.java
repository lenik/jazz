package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdProcess;
import net.bodz.violet.fab.FabStdProcessSamples;

public class FabStdProcessMapperTest
        extends AbstractTableTest<FabStdProcess, FabStdProcessMapper> {

    @Override
    public FabStdProcess buildSample()
            throws Exception {
        FabStdProcessSamples a = new FabStdProcessSamples();
        return a.buildWired(tables);
    }

}
