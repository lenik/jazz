package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabFn;
import net.bodz.violet.fab.FabFnSamples;

public class FabFnMapperTest
        extends AbstractMapperTest<FabFn, FabFnMask, FabFnMapper> {

    @Override
    public FabFn buildSample() {
        return FabFnSamples.build();
    }

}
