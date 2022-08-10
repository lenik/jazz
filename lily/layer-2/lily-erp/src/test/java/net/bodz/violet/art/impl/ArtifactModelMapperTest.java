package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.ArtifactModelSamples;

public class ArtifactModelMapperTest
        extends AbstractMapperTest<ArtifactModel, ArtifactModelMask, ArtifactModelMapper> {

    @Override
    public ArtifactModel buildSample() {
        ArtifactModel obsolete = tables.pickAny(ArtifactModelMapper.class, "artmodel");
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return ArtifactModelSamples.build(obsolete, artifact);
    }

}
