package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.PlanPhase;
import net.bodz.violet.plan.PlanPhaseSamples;

public class PlanPhaseManagerTest
        extends AbstractManagerTest<PlanPhase, PlanPhaseMapper, PlanPhaseManager> {

    @Override
    public PlanPhase buildSample()
            throws Exception {
        PlanPhaseSamples a = new PlanPhaseSamples();
        return a.buildWired(tables);
    }

}
