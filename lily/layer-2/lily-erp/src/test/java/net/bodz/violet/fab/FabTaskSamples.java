package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabTaskSamples
        extends TestSamples {

    public static FabTask build() {
        FabTask a = new FabTask();
        a.setLabel("fabTask-1");
        a.setDescription("A fabTask named fabTask-1.");
        return a;
    }

}
