package net.bodz.violet.shop;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopItemFavSamples
        extends TestSampleBuilder {

    public ShopItem shopItem;
    public User user;

    public ShopItemFav build()
            throws Exception {
        ShopItemFav a = new ShopItemFav();
        a.setUser(user);
        return a;
    }

}
