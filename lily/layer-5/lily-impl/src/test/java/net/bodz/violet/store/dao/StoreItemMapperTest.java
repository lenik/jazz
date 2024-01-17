package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.StoreItem;
import net.bodz.violet.store.StoreItemSamples;

public class StoreItemMapperTest
        extends AbstractTableTest<StoreItem, StoreItemCriteriaBuilder, StoreItemMapper> {

    @Override
    public StoreItem buildSample()
            throws Exception {
        StoreItemSamples a = new StoreItemSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
