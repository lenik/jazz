package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabTaskItemSamples
        extends TestSamples {

    public static FabTaskItem build() {
        FabTaskItem a = new FabTaskItem();
        a.setLabel("fabTaskItem-1");
        a.setDescription("A fabTaskItem named fabTaskItem-1.");
        return a;
    }

}
