package net.bodz.violet.manu;

import net.bodz.lily.test.TestSamples;

public class ManuTaskItemSamples
        extends TestSamples {

    public static ManuTaskItem build() {
        ManuTaskItem a = new ManuTaskItem();
        a.setLabel("manuTaskItem-1");
        a.setDescription("A manuTaskItem named manuTaskItem-1.");
        return a;
    }

}
