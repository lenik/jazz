package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractTableTest<RegionCategory, RegionCategoryMask, RegionCategoryMapper> {

    @Override
    public RegionCategory buildSample()
            throws Exception {
        RegionCategorySamples a = new RegionCategorySamples();
        a.parent = tables.pickAny(RegionCategoryMapper.class, "regioncat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
