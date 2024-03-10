package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppCategory;
import net.bodz.lily.schema.vapp.VAppCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class VAppCategoryMapperTest
        extends AbstractTableTest<VAppCategory, VAppCategoryMapper> {

    @Override
    public VAppCategory buildSample()
            throws Exception {
        VAppCategorySamples a = new VAppCategorySamples();
        return a.buildWired(tables);
    }

}
