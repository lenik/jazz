package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactBackref;
import net.bodz.violet.art.ArtifactBackrefSamples;

public class ArtifactBackrefMapperTest
        extends AbstractTableTest<ArtifactBackref, ArtifactBackrefMask, ArtifactBackrefMapper> {

    @Override
    public ArtifactBackref buildSample() {
        return ArtifactBackrefSamples.build();
    }

}
