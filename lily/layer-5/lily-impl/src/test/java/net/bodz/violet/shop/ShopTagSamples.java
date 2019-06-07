package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class ShopTagSamples
        extends TestSamples {

    public static ShopTag build() {
        ShopTag a = new ShopTag();
        a.setLabel("shopTag-1");
        a.setDescription("A shopTag named shopTag-1.");
        return a;
    }

}
