package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactSamples;

public class ArtifactMapperTest
        extends AbstractTableTest<Artifact, ArtifactMapper> {

    @Override
    public Artifact buildSample()
            throws Exception {
        ArtifactSamples a = new ArtifactSamples();
        return a.buildWired(tables);
    }

}
