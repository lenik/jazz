package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class PlanTagSamples
        extends TestSamples {

    public static PlanTag build() {
        PlanTag a = new PlanTag();
        a.setLabel("planTag-1");
        a.setDescription("A planTag named planTag-1.");
        return a;
    }

}
