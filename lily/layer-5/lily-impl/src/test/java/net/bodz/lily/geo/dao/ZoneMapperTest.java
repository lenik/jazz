package net.bodz.lily.geo.dao;

import net.bodz.lily.geo.Zone;
import net.bodz.lily.geo.ZoneSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ZoneMapperTest
        extends AbstractTableTest<Zone, ZoneMask, ZoneMapper> {

    @Override
    public Zone buildSample()
            throws Exception {
        ZoneSamples a = new ZoneSamples();
        a.parent = tables.pickAny(ZoneMapper.class, "zone");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.category = tables.pickAny(ZoneCategoryMapper.class, "zonecat");
        return a.build();
    }

}
