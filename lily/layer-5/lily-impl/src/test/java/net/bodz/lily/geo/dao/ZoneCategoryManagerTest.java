package net.bodz.lily.geo.dao;

import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneCategorySamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ZoneCategoryManagerTest
        extends AbstractManagerTest<ZoneCategory, ZoneCategoryMapper, ZoneCategoryManager> {

    @Override
    public ZoneCategory buildSample()
            throws Exception {
        ZoneCategorySamples a = new ZoneCategorySamples();
        return a.buildWired(tables);
    }

}
