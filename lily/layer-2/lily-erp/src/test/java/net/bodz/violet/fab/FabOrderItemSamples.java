package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabOrderItemSamples
        extends TestSamples {

    public static FabOrderItem build() {
        FabOrderItem a = new FabOrderItem();
        a.setLabel("fabOrderItem-1");
        a.setDescription("A fabOrderItem named fabOrderItem-1.");
        return a;
    }

}
