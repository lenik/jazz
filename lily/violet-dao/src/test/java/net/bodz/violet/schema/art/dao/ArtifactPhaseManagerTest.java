package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactPhase;
import net.bodz.violet.schema.art.ArtifactPhaseSamples;

public class ArtifactPhaseManagerTest
        extends AbstractManagerTest<ArtifactPhase, ArtifactPhaseMapper, ArtifactPhaseManager> {

    @Override
    public ArtifactPhase buildSample()
            throws Exception {
        ArtifactPhaseSamples a = new ArtifactPhaseSamples();
        return a.buildWired(tables);
    }

}
