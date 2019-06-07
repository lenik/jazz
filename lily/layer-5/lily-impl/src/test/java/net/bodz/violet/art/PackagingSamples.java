package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class PackagingSamples
        extends TestSamples {

    public static Packaging build() {
        Packaging a = new Packaging();
        a.setLabel("packaging-1");
        a.setDescription("A packaging named packaging-1.");
        return a;
    }

}
