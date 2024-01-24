package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabStdTestParameter;
import net.bodz.violet.schema.fab.FabStdTestParameterSamples;

public class FabStdTestParameterMapperTest
        extends AbstractTableTest<FabStdTestParameter, FabStdTestParameterMapper> {

    @Override
    public FabStdTestParameter buildSample()
            throws Exception {
        FabStdTestParameterSamples a = new FabStdTestParameterSamples();
        return a.buildWired(tables);
    }

}
