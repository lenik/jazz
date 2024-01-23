package net.bodz.lily.geo.dao;

import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class ZoneCategoryMapperTest
        extends AbstractTableTest<ZoneCategory, ZoneCategoryMapper> {

    @Override
    public ZoneCategory buildSample()
            throws Exception {
        ZoneCategorySamples a = new ZoneCategorySamples();
        return a.buildWired(tables);
    }

}
