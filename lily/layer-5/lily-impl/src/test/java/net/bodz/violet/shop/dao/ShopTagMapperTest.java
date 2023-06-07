package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopTag;
import net.bodz.violet.shop.ShopTagSamples;

public class ShopTagMapperTest
        extends AbstractTableTest<ShopTag, ShopTagMask, ShopTagMapper> {

    @Override
    public ShopTag buildSample()
            throws Exception {
        ShopTagSamples a = new ShopTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(ShopTagMapper.class, "shoptag");
        return a.build();
    }

}
