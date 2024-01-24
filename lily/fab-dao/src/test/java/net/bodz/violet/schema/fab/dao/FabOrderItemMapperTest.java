package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabOrderItem;
import net.bodz.violet.schema.fab.FabOrderItemSamples;

public class FabOrderItemMapperTest
        extends AbstractTableTest<FabOrderItem, FabOrderItemMapper> {

    @Override
    public FabOrderItem buildSample()
            throws Exception {
        FabOrderItemSamples a = new FabOrderItemSamples();
        return a.buildWired(tables);
    }

}
