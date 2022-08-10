package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabStdTester;
import net.bodz.violet.fab.FabStdTesterSamples;

public class FabStdTesterMapperTest
        extends AbstractMapperTest<FabStdTester, FabStdTesterMask, FabStdTesterMapper> {

    @Override
    public FabStdTester buildSample() {
        return FabStdTesterSamples.build();
    }

}
