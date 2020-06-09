package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabFn;
import net.bodz.violet.fab.FabFnSamples;

public class FabFnMapperTest
        extends AbstractMapperTest<FabFn, FabFnMask, FabFnMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabFn buildSample() {
        return FabFnSamples.build();
    }

}
