package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabOrder;
import net.bodz.violet.fab.FabOrderSamples;

public class FabOrderMapperTest
        extends AbstractTableTest<FabOrder, FabOrderMapper> {

    @Override
    public FabOrder buildSample()
            throws Exception {
        FabOrderSamples a = new FabOrderSamples();
        return a.buildWired(tables);
    }

}
