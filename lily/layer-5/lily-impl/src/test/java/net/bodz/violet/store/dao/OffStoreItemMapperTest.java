package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.OffStoreItem;
import net.bodz.violet.store.OffStoreItemSamples;

public class OffStoreItemMapperTest
        extends AbstractTableTest<OffStoreItem, OffStoreItemCriteriaBuilder, OffStoreItemMapper> {

    @Override
    public OffStoreItem buildSample()
            throws Exception {
        OffStoreItemSamples a = new OffStoreItemSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
