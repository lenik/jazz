package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.ShopItemFav;
import net.bodz.violet.shop.ShopItemFavSamples;

public class ShopItemFavMapperTest
        extends AbstractTableTest<ShopItemFav, ShopItemFavCriteriaBuilder, ShopItemFavMapper> {

    @Override
    public ShopItemFav buildSample()
            throws Exception {
        ShopItemFavSamples a = new ShopItemFavSamples();
        a.shopItem = tables.pickAny(ShopItemMapper.class, "shopitem");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
