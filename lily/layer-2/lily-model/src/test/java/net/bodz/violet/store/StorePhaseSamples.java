package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;

public class StorePhaseSamples
        extends TestSamples {

    public static StorePhase build() {
        StorePhase a = new StorePhase();
        a.setLabel("storePhase-1");
        a.setDescription("A storePhase named storePhase-1.");
        return a;
    }

}
