package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabConsumableSamples
        extends TestSamples {

    public static FabConsumable build() {
        FabConsumable a = new FabConsumable();
        a.setLabel("fabConsumable-1");
        a.setDescription("A fabConsumable named fabConsumable-1.");
        return a;
    }

}
