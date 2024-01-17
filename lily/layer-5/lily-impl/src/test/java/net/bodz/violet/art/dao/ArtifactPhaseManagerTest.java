package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseManagerTest
        extends AbstractManagerTest<ArtifactPhase, ArtifactPhaseMapper, ArtifactPhaseManager> {

    @Override
    public ArtifactPhase buildSample()
            throws Exception {
        ArtifactPhaseSamples a = new ArtifactPhaseSamples();
        return a.buildWired(tables);
    }

}
