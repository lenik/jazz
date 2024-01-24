package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.RegionCategory;
import net.bodz.violet.schema.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractTableTest<RegionCategory, RegionCategoryMapper> {

    @Override
    public RegionCategory buildSample()
            throws Exception {
        RegionCategorySamples a = new RegionCategorySamples();
        return a.buildWired(tables);
    }

}
