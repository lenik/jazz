package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractTableTest<ArtifactPhase, ArtifactPhaseMapper> {

    @Override
    public ArtifactPhase buildSample()
            throws Exception {
        ArtifactPhaseSamples a = new ArtifactPhaseSamples();
        return a.buildWired(tables);
    }

}
