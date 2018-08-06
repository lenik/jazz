package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class ShopSamples
        extends TestSamples {

    public static Shop build() {
        Shop a = new Shop();
        a.setLabel("shop-1");
        a.setDescription("A shop named shop-1.");
        return a;
    }

}
