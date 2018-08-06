package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class ShopItemCategorySamples
        extends TestSamples {

    public static ShopItemCategory build() {
        ShopItemCategory a = new ShopItemCategory();
        a.setLabel("shopItemCategory-1");
        a.setDescription("A shopItemCategory named shopItemCategory-1.");
        return a;
    }

}
