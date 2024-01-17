package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopCategory;
import net.bodz.violet.shop.ShopCategorySamples;

public class ShopCategoryMapperTest
        extends AbstractTableTest<ShopCategory, ShopCategoryCriteriaBuilder, ShopCategoryMapper> {

    @Override
    public ShopCategory buildSample()
            throws Exception {
        ShopCategorySamples a = new ShopCategorySamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(ShopCategoryMapper.class, "shopcat");
        return a.build();
    }

}
