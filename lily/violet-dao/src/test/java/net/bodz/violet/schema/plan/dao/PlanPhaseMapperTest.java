package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.PlanPhase;
import net.bodz.violet.schema.plan.PlanPhaseSamples;

public class PlanPhaseMapperTest
        extends AbstractTableTest<PlanPhase, PlanPhaseMapper> {

    @Override
    public PlanPhase buildSample()
            throws Exception {
        PlanPhaseSamples a = new PlanPhaseSamples();
        return a.buildWired(tables);
    }

}
