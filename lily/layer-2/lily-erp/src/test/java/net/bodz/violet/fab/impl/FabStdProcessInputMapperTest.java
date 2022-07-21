package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabStdProcessInput;
import net.bodz.violet.fab.FabStdProcessInputSamples;

public class FabStdProcessInputMapperTest
        extends AbstractMapperTest<FabStdProcessInput, FabStdProcessInputMask, FabStdProcessInputMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabStdProcessInput buildSample() {
        return FabStdProcessInputSamples.build();
    }

}
