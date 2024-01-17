package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopItemCategory;
import net.bodz.violet.shop.ShopItemCategorySamples;

public class ShopItemCategoryMapperTest
        extends AbstractTableTest<ShopItemCategory, ShopItemCategoryCriteriaBuilder, ShopItemCategoryMapper> {

    @Override
    public ShopItemCategory buildSample()
            throws Exception {
        ShopItemCategorySamples a = new ShopItemCategorySamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(ShopItemCategoryMapper.class, "shopitemcat");
        return a.build();
    }

}
