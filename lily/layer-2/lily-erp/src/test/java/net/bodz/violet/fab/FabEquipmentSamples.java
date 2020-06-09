package net.bodz.violet.fab;

import net.bodz.lily.test.TestSamples;

public class FabEquipmentSamples
        extends TestSamples {

    public static FabEquipment build() {
        FabEquipment a = new FabEquipment();
        a.setLabel("fabEquipment-1");
        a.setDescription("A fabEquipment named fabEquipment-1.");
        return a;
    }

}
