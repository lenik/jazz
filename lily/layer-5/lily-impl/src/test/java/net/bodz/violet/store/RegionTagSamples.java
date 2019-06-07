package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;

public class RegionTagSamples
        extends TestSamples {

    public static RegionTag build() {
        RegionTag a = new RegionTag();
        a.setLabel("regionTag-1");
        a.setDescription("A regionTag named regionTag-1.");
        return a;
    }

}
