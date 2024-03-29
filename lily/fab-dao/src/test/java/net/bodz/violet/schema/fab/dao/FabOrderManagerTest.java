package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabOrder;
import net.bodz.violet.schema.fab.FabOrderSamples;

public class FabOrderManagerTest
        extends AbstractManagerTest<FabOrder, FabOrderMapper, FabOrderManager> {

    @Override
    public FabOrder buildSample()
            throws Exception {
        FabOrderSamples a = new FabOrderSamples();
        return a.buildWired(tables);
    }

}
