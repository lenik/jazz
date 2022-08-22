package net.bodz.lily.geo.impl;

import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class ZoneCategoryMapperTest
        extends AbstractTableTest<ZoneCategory, ZoneCategoryMask, ZoneCategoryMapper> {

    @Override
    public ZoneCategory buildSample() {
        return ZoneCategorySamples.build();
    }

}
