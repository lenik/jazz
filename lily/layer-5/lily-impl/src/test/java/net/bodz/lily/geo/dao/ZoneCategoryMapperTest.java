package net.bodz.lily.geo.dao;

import net.bodz.lily.geo.ZoneCategory;
import net.bodz.lily.geo.ZoneCategorySamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ZoneCategoryMapperTest
        extends AbstractTableTest<ZoneCategory, ZoneCategoryCriteriaBuilder, ZoneCategoryMapper> {

    @Override
    public ZoneCategory buildSample()
            throws Exception {
        ZoneCategorySamples a = new ZoneCategorySamples();
        a.parent = tables.pickAny(ZoneCategoryMapper.class, "zonecat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
