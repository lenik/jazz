package net.bodz.violet.tran.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportCategorySamples;

public class TransportCategoryMapperTest
        extends AbstractMapperTest<TransportCategory, TransportCategoryMask, TransportCategoryMapper> {

    @Override
    public TransportCategory buildSample() {
        return TransportCategorySamples.build();
    }

}
