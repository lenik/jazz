package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabOrderItem;
import net.bodz.violet.fab.FabOrderItemSamples;

public class FabOrderItemMapperTest
        extends AbstractTableTest<FabOrderItem, FabOrderItemMapper> {

    @Override
    public FabOrderItem buildSample()
            throws Exception {
        FabOrderItemSamples a = new FabOrderItemSamples();
        return a.buildWired(tables);
    }

}
