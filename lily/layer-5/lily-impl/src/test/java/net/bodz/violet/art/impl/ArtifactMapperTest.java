package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactSamples;

public class ArtifactMapperTest
        extends AbstractMapperTest<Artifact, ArtifactMask, ArtifactMapper> {

    @Override
    public Artifact buildSample() {
        return ArtifactSamples.build();
    }

}
