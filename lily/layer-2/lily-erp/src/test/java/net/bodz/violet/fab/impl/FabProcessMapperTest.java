package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabProcess;
import net.bodz.violet.fab.FabProcessSamples;

public class FabProcessMapperTest
        extends AbstractTableTest<FabProcess, FabProcessMask, FabProcessMapper> {

    @Override
    public FabProcess buildSample() {
        return FabProcessSamples.build();
    }

}
