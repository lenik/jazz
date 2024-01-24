package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabFn;
import net.bodz.violet.schema.fab.FabFnSamples;

public class FabFnMapperTest
        extends AbstractTableTest<FabFn, FabFnMapper> {

    @Override
    public FabFn buildSample()
            throws Exception {
        FabFnSamples a = new FabFnSamples();
        return a.buildWired(tables);
    }

}
