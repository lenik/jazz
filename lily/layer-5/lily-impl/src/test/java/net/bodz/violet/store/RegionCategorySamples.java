package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;

public class RegionCategorySamples
        extends TestSamples {

    public static RegionCategory build() {
        RegionCategory a = new RegionCategory();
        a.setLabel("regionCategory-1");
        a.setDescription("A regionCategory named regionCategory-1.");
        return a;
    }

}
