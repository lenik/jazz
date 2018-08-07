package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuOrderItemSamples
        extends TestSamples {

    public static ManuOrderItem build() {
        ManuOrderItem a = new ManuOrderItem();
        a.setLabel("manuOrderItem-1");
        a.setDescription("A manuOrderItem named manuOrderItem-1.");
        return a;
    }

}
