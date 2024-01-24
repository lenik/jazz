package net.bodz.lily.schema.geo.dao;

import net.bodz.lily.schema.geo.ZoneCategory;
import net.bodz.lily.schema.geo.ZoneCategorySamples;
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
