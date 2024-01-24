package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.tran.TransportCategory;
import net.bodz.violet.schema.tran.TransportCategorySamples;

public class TransportCategoryManagerTest
        extends AbstractManagerTest<TransportCategory, TransportCategoryMapper, TransportCategoryManager> {

    @Override
    public TransportCategory buildSample()
            throws Exception {
        TransportCategorySamples a = new TransportCategorySamples();
        return a.buildWired(tables);
    }

}
