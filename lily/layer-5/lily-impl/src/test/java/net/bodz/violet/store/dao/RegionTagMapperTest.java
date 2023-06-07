package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionTag;
import net.bodz.violet.store.RegionTagSamples;

public class RegionTagMapperTest
        extends AbstractTableTest<RegionTag, RegionTagMask, RegionTagMapper> {

    @Override
    public RegionTag buildSample()
            throws Exception {
        RegionTagSamples a = new RegionTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(RegionTagMapper.class, "regiontag");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
