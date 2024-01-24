package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.PlanPhase;
import net.bodz.violet.schema.plan.PlanPhaseSamples;

public class PlanPhaseManagerTest
        extends AbstractManagerTest<PlanPhase, PlanPhaseMapper, PlanPhaseManager> {

    @Override
    public PlanPhase buildSample()
            throws Exception {
        PlanPhaseSamples a = new PlanPhaseSamples();
        return a.buildWired(tables);
    }

}
