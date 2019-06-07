package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class ShopCategorySamples
        extends TestSamples {

    public static ShopCategory build() {
        ShopCategory a = new ShopCategory();
        a.setLabel("shopCategory-1");
        a.setDescription("A shopCategory named shopCategory-1.");
        return a;
    }

}
