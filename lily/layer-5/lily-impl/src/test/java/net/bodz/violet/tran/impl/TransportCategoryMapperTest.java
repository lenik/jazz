package net.bodz.violet.tran.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportCategorySamples;

public class TransportCategoryMapperTest
        extends AbstractTableTest<TransportCategory, TransportCategoryMask, TransportCategoryMapper> {

    @Override
    public TransportCategory buildSample() {
        return TransportCategorySamples.build();
    }

}
