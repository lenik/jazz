package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopItemMapper;

public class ShopItemFavSamples
        extends TestSampleBuilder {

    public ShopItem shopItem;
    public User user;

    @Override
    public ShopItemFav build()
            throws Exception {
        ShopItemFav a = new ShopItemFav();
        a.setShopItem(shopItem);
        a.setUser(user);
        return a;
    }

    @Override
    public ShopItemFavSamples wireAny(IRandomPicker picker) {
        this.shopItem = picker.pickAny(ShopItemMapper.class, "shopitem");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ShopItemFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
