package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdProcessInput;
import net.bodz.violet.schema.fab.FabStdProcessInputSamples;

public class FabStdProcessInputMapperTest
        extends AbstractTableTest<FabStdProcessInput, FabStdProcessInputMapper> {

    @Override
    public FabStdProcessInput buildSample()
            throws Exception {
        FabStdProcessInputSamples a = new FabStdProcessInputSamples();
        return a.buildWired(tables);
    }

}
