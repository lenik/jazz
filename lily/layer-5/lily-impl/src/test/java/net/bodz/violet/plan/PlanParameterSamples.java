package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class PlanParameterSamples
        extends TestSamples {

    public static PlanParameter build() {
        PlanParameter a = new PlanParameter();
        a.setLabel("planParameter-1");
        a.setDescription("A planParameter named planParameter-1.");
        return a;
    }

}
