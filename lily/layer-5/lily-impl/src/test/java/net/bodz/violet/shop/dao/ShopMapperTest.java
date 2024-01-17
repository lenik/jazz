package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopSamples;

public class ShopMapperTest
        extends AbstractTableTest<Shop, ShopCriteriaBuilder, ShopMapper> {

    @Override
    public Shop buildSample()
            throws Exception {
        ShopSamples a = new ShopSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
