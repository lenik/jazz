package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuTaskSamples
        extends TestSamples {

    public static ManuTask build() {
        ManuTask a = new ManuTask();
        a.setLabel("manuTask-1");
        a.setDescription("A manuTask named manuTask-1.");
        return a;
    }

}
