package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTestParameter;
import net.bodz.violet.fab.FabStdTestParameterSamples;

public class FabStdTestParameterMapperTest
        extends AbstractTableTest<FabStdTestParameter, FabStdTestParameterMapper> {

    @Override
    public FabStdTestParameter buildSample()
            throws Exception {
        FabStdTestParameterSamples a = new FabStdTestParameterSamples();
        return a.buildWired(tables);
    }

}
