package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractMapperTest<ArtifactPhase, ArtifactPhaseMask, ArtifactPhaseMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArtifactPhase buildSample() {
        return ArtifactPhaseSamples.build();
    }

}
