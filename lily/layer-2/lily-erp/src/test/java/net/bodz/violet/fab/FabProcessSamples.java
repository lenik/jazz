package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabProcessSamples
        extends TestSamples {

    public static FabProcess build() {
        FabProcess a = new FabProcess();
        a.setLabel("fabProcess-1");
        a.setDescription("A fabProcess named fabProcess-1.");
        return a;
    }

}
