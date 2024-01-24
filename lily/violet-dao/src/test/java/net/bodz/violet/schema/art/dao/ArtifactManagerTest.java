package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.ArtifactSamples;

public class ArtifactManagerTest
        extends AbstractManagerTest<Artifact, ArtifactMapper, ArtifactManager> {

    @Override
    public Artifact buildSample()
            throws Exception {
        ArtifactSamples a = new ArtifactSamples();
        return a.buildWired(tables);
    }

}
