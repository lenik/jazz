package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractMapperTest<ArtifactPhase, ArtifactPhaseMask, ArtifactPhaseMapper> {

    @Override
    public ArtifactPhase buildSample() {
        return ArtifactPhaseSamples.build();
    }

}
