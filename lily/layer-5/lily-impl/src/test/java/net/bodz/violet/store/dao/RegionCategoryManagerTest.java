package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryManagerTest
        extends AbstractManagerTest<RegionCategory, RegionCategoryMapper, RegionCategoryManager> {

    @Override
    public RegionCategory buildSample()
            throws Exception {
        RegionCategorySamples a = new RegionCategorySamples();
        return a.buildWired(tables);
    }

}
