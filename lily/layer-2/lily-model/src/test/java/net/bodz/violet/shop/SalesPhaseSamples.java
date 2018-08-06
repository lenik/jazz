package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class SalesPhaseSamples
        extends TestSamples {

    public static SalesPhase build() {
        SalesPhase a = new SalesPhase();
        a.setLabel("salesPhase-1");
        a.setDescription("A salesPhase named salesPhase-1.");
        return a;
    }

}
