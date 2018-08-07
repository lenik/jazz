package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuConsumableSamples
        extends TestSamples {

    public static ManuConsumable build() {
        ManuConsumable a = new ManuConsumable();
        a.setLabel("manuConsumable-1");
        a.setDescription("A manuConsumable named manuConsumable-1.");
        return a;
    }

}
