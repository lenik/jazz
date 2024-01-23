package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactSamples;

public class ArtifactManagerTest
        extends AbstractManagerTest<Artifact, ArtifactMapper, ArtifactManager> {

    @Override
    public Artifact buildSample()
            throws Exception {
        ArtifactSamples a = new ArtifactSamples();
        return a.buildWired(tables);
    }

}
