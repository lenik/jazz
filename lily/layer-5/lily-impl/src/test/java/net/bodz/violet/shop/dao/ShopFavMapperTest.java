package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopFav;
import net.bodz.violet.shop.ShopFavSamples;

public class ShopFavMapperTest
        extends AbstractTableTest<ShopFav, ShopFavCriteriaBuilder, ShopFavMapper> {

    @Override
    public ShopFav buildSample()
            throws Exception {
        ShopFavSamples a = new ShopFavSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.shop = tables.pickAny(ShopMapper.class, "shop");
        return a.build();
    }

}
