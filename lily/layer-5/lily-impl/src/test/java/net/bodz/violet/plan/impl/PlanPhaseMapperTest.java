package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.plan.PlanPhase;
import net.bodz.violet.plan.PlanPhaseSamples;

public class PlanPhaseMapperTest
        extends AbstractMapperTest<PlanPhase, PlanPhaseMask, PlanPhaseMapper> {

    @Override
    public PlanPhase buildSample() {
        return PlanPhaseSamples.build();
    }

}
