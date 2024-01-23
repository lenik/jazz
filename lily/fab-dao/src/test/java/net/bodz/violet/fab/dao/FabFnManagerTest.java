package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabFn;
import net.bodz.violet.fab.FabFnSamples;

public class FabFnManagerTest
        extends AbstractManagerTest<FabFn, FabFnMapper, FabFnManager> {

    @Override
    public FabFn buildSample()
            throws Exception {
        FabFnSamples a = new FabFnSamples();
        return a.buildWired(tables);
    }

}
