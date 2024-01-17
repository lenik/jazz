package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractTableTest<RegionCategory, RegionCategoryMapper> {

    @Override
    public RegionCategory buildSample()
            throws Exception {
        RegionCategorySamples a = new RegionCategorySamples();
        return a.buildWired(tables);
    }

}
