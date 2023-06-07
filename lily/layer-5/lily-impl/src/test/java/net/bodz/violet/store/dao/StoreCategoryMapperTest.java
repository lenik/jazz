package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StoreCategory;
import net.bodz.violet.store.StoreCategorySamples;

public class StoreCategoryMapperTest
        extends AbstractTableTest<StoreCategory, StoreCategoryMask, StoreCategoryMapper> {

    @Override
    public StoreCategory buildSample()
            throws Exception {
        StoreCategorySamples a = new StoreCategorySamples();
        a.parent = tables.pickAny(StoreCategoryMapper.class, "storecat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
