package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryPhase;
import net.bodz.violet.schema.plan.DiaryPhaseSamples;

public class DiaryPhaseMapperTest
        extends AbstractTableTest<DiaryPhase, DiaryPhaseMapper> {

    @Override
    public DiaryPhase buildSample()
            throws Exception {
        DiaryPhaseSamples a = new DiaryPhaseSamples();
        return a.buildWired(tables);
    }

}
