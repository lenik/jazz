package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportCategorySamples;

public class TransportCategoryManagerTest
        extends AbstractManagerTest<TransportCategory, TransportCategoryMapper, TransportCategoryManager> {

    @Override
    public TransportCategory buildSample()
            throws Exception {
        TransportCategorySamples a = new TransportCategorySamples();
        return a.buildWired(tables);
    }

}
