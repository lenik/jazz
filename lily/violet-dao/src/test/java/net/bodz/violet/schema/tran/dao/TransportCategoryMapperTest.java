package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.tran.TransportCategory;
import net.bodz.violet.schema.tran.TransportCategorySamples;

public class TransportCategoryMapperTest
        extends AbstractTableTest<TransportCategory, TransportCategoryMapper> {

    @Override
    public TransportCategory buildSample()
            throws Exception {
        TransportCategorySamples a = new TransportCategorySamples();
        return a.buildWired(tables);
    }

}
