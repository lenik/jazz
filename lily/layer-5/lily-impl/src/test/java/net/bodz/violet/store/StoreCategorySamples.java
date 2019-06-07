package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;

public class StoreCategorySamples
        extends TestSamples {

    public static StoreCategory build() {
        StoreCategory a = new StoreCategory();
        a.setLabel("storeCategory-1");
        a.setDescription("A storeCategory named storeCategory-1.");
        return a;
    }

}
