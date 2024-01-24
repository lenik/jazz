package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabTaskItem;
import net.bodz.violet.schema.fab.FabTaskItemSamples;

public class FabTaskItemMapperTest
        extends AbstractTableTest<FabTaskItem, FabTaskItemMapper> {

    @Override
    public FabTaskItem buildSample()
            throws Exception {
        FabTaskItemSamples a = new FabTaskItemSamples();
        return a.buildWired(tables);
    }

}
