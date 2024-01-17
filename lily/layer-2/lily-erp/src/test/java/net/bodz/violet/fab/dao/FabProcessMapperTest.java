package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabProcess;
import net.bodz.violet.fab.FabProcessSamples;

public class FabProcessMapperTest
        extends AbstractTableTest<FabProcess, FabProcessMapper> {

    @Override
    public FabProcess buildSample()
            throws Exception {
        FabProcessSamples a = new FabProcessSamples();
        return a.buildWired(tables);
    }

}
