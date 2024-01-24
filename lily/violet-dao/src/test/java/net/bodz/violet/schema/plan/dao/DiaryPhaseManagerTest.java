package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryPhase;
import net.bodz.violet.schema.plan.DiaryPhaseSamples;

public class DiaryPhaseManagerTest
        extends AbstractManagerTest<DiaryPhase, DiaryPhaseMapper, DiaryPhaseManager> {

    @Override
    public DiaryPhase buildSample()
            throws Exception {
        DiaryPhaseSamples a = new DiaryPhaseSamples();
        return a.buildWired(tables);
    }

}
