package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.DiaryPhase;
import net.bodz.violet.plan.DiaryPhaseSamples;

public class DiaryPhaseMapperTest
        extends AbstractMapperTest<DiaryPhase, DiaryPhaseMask, DiaryPhaseMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public DiaryPhase buildSample() {
        return DiaryPhaseSamples.build();
    }

}
