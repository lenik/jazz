package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabStdTest;
import net.bodz.violet.fab.FabStdTestSamples;

public class FabStdTestMapperTest
        extends AbstractTableTest<FabStdTest, FabStdTestMask, FabStdTestMapper> {

    @Override
    public FabStdTest buildSample() {
        return FabStdTestSamples.build();
    }

}
