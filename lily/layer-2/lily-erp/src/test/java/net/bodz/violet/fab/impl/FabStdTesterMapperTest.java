package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTester;
import net.bodz.violet.fab.FabStdTesterSamples;

public class FabStdTesterMapperTest
        extends AbstractTableTest<FabStdTester, FabStdTesterMask, FabStdTesterMapper> {

    @Override
    public FabStdTester buildSample() {
        return FabStdTesterSamples.build();
    }

}
