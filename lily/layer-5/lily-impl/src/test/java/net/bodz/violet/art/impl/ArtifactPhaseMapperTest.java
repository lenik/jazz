package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractTableTest<ArtifactPhase, ArtifactPhaseMask, ArtifactPhaseMapper> {

    @Override
    public ArtifactPhase buildSample() {
        return ArtifactPhaseSamples.build();
    }

}
