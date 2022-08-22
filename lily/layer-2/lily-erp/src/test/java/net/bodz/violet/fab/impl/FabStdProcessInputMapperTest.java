package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdProcessInput;
import net.bodz.violet.fab.FabStdProcessInputSamples;

public class FabStdProcessInputMapperTest
        extends AbstractTableTest<FabStdProcessInput, FabStdProcessInputMask, FabStdProcessInputMapper> {

    @Override
    public FabStdProcessInput buildSample() {
        return FabStdProcessInputSamples.build();
    }

}
