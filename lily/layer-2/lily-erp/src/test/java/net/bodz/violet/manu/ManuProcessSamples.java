package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuProcessSamples
        extends TestSamples {

    public static ManuProcess build() {
        ManuProcess a = new ManuProcess();
        a.setLabel("manuProcess-1");
        a.setDescription("A manuProcess named manuProcess-1.");
        return a;
    }

}
