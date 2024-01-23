package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdProcessInput;
import net.bodz.violet.fab.FabStdProcessInputSamples;

public class FabStdProcessInputMapperTest
        extends AbstractTableTest<FabStdProcessInput, FabStdProcessInputMapper> {

    @Override
    public FabStdProcessInput buildSample()
            throws Exception {
        FabStdProcessInputSamples a = new FabStdProcessInputSamples();
        return a.buildWired(tables);
    }

}
