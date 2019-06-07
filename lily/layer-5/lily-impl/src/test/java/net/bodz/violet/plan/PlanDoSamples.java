package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class PlanDoSamples
        extends TestSamples {

    public static PlanDo build(Plan plan, PlanDo parent) {
        PlanDo a = new PlanDo();
        a.setSubject("planDo-1");
        a.setText("A planDo named planDo-1.");
        a.setPlan(plan);
        if (random.nextInt(100) < 30)
            a.setParent(parent);
        return a;
    }

}
