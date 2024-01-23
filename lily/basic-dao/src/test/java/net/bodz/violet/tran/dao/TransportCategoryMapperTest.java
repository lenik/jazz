package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportCategorySamples;

public class TransportCategoryMapperTest
        extends AbstractTableTest<TransportCategory, TransportCategoryMapper> {

    @Override
    public TransportCategory buildSample()
            throws Exception {
        TransportCategorySamples a = new TransportCategorySamples();
        return a.buildWired(tables);
    }

}
