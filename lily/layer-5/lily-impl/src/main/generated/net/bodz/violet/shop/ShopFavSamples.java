package net.bodz.violet.shop;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopFavSamples
        extends TestSampleBuilder {

    public User user;
    public Shop shop;

    public ShopFav build()
            throws Exception {
        ShopFav a = new ShopFav();
        a.setUser(user);
        a.setShop(shop);
        return a;
    }

}
