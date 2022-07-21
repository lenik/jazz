package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractMapperTest<ArtifactPhase, ArtifactPhaseMask, ArtifactPhaseMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArtifactPhase buildSample() {
        return ArtifactPhaseSamples.build();
    }

}
