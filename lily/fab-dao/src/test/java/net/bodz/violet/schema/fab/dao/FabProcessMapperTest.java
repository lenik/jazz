package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabProcess;
import net.bodz.violet.schema.fab.FabProcessSamples;

public class FabProcessMapperTest
        extends AbstractTableTest<FabProcess, FabProcessMapper> {

    @Override
    public FabProcess buildSample()
            throws Exception {
        FabProcessSamples a = new FabProcessSamples();
        return a.buildWired(tables);
    }

}
