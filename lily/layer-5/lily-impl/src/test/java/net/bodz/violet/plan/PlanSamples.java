package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class PlanSamples
        extends TestSamples {

    public static Plan build(User op, PlanCategory category, PlanPhase phase) {
        Plan a = new Plan();
        a.setSubject("plan-1");
        a.setRawText("A plan named plan-1.");

        a.setCategory(category);
        a.setPhase(phase);

        a.setValue(random.nextInt(100000) / 100.0);
        return a;
    }

}
