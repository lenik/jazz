package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class PlanPhaseSamples
        extends TestSamples {

    public static PlanPhase build() {
        PlanPhase a = new PlanPhase();
        a.setLabel("planPhase-1");
        a.setDescription("A planPhase named planPhase-1.");
        return a;
    }

}
