package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdProcess;
import net.bodz.violet.fab.FabStdProcessSamples;

public class FabStdProcessMapperTest
        extends AbstractTableTest<FabStdProcess, FabStdProcessMask, FabStdProcessMapper> {

    @Override
    public FabStdProcess buildSample() {
        return FabStdProcessSamples.build();
    }

}
