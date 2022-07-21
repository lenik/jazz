package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactSamples;

public class ArtifactMapperTest
        extends AbstractMapperTest<Artifact, ArtifactMask, ArtifactMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Artifact buildSample() {
        return ArtifactSamples.build();
    }

}
