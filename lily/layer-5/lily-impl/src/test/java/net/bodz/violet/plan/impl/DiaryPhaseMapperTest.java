package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryPhase;
import net.bodz.violet.plan.DiaryPhaseSamples;

public class DiaryPhaseMapperTest
        extends AbstractTableTest<DiaryPhase, DiaryPhaseMask, DiaryPhaseMapper> {

    @Override
    public DiaryPhase buildSample() {
        return DiaryPhaseSamples.build();
    }

}
