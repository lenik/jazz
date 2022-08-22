package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTestParameter;
import net.bodz.violet.fab.FabStdTestParameterSamples;

public class FabStdTestParameterMapperTest
        extends AbstractTableTest<FabStdTestParameter, FabStdTestParameterMask, FabStdTestParameterMapper> {

    @Override
    public FabStdTestParameter buildSample() {
        return FabStdTestParameterSamples.build();
    }

}
