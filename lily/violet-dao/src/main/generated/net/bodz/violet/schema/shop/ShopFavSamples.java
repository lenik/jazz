package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopMapper;

public class ShopFavSamples
        extends TestSampleBuilder {

    public User user;
    public Shop shop;

    @Override
    public ShopFav build()
            throws Exception {
        ShopFav a = new ShopFav();
        a.setUser(user);
        a.setShop(shop);
        return a;
    }

    @Override
    public ShopFavSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.shop = picker.pickAny(ShopMapper.class, "shop");
        return this;
    }

    @Override
    public ShopFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
