package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabOrderSamples
        extends TestSamples {

    public static FabOrder build() {
        FabOrder a = new FabOrder();
        a.setLabel("fabOrder-1");
        a.setDescription("A fabOrder named fabOrder-1.");
        return a;
    }

}
