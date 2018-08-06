package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class SalesCategorySamples
        extends TestSamples {

    public static SalesCategory build() {
        SalesCategory a = new SalesCategory();
        a.setLabel("salesCategory-1");
        a.setDescription("A salesCategory named salesCategory-1.");
        return a;
    }

}
