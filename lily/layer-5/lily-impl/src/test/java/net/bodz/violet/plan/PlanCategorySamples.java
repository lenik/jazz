package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class PlanCategorySamples
        extends TestSamples {

    public static PlanCategory build() {
        PlanCategory a = new PlanCategory();
        a.setLabel("planCategory-1");
        a.setDescription("A planCategory named planCategory-1.");
        return a;
    }

}
