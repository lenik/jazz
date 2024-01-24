package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactPhase;
import net.bodz.violet.schema.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractTableTest<ArtifactPhase, ArtifactPhaseMapper> {

    @Override
    public ArtifactPhase buildSample()
            throws Exception {
        ArtifactPhaseSamples a = new ArtifactPhaseSamples();
        return a.buildWired(tables);
    }

}
