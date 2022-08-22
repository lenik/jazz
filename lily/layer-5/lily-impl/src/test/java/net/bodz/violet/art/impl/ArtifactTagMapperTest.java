package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactTag;
import net.bodz.violet.art.ArtifactTagSamples;

public class ArtifactTagMapperTest
        extends AbstractTableTest<ArtifactTag, ArtifactTagMask, ArtifactTagMapper> {

    @Override
    public ArtifactTag buildSample() {
        return ArtifactTagSamples.build();
    }

}
