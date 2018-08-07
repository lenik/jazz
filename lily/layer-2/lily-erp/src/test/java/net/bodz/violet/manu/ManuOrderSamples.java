package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuOrderSamples
        extends TestSamples {

    public static ManuOrder build() {
        ManuOrder a = new ManuOrder();
        a.setLabel("manuOrder-1");
        a.setDescription("A manuOrder named manuOrder-1.");
        return a;
    }

}
