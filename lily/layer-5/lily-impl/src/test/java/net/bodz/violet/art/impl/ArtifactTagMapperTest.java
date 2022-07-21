package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.ArtifactTag;
import net.bodz.violet.art.ArtifactTagSamples;

public class ArtifactTagMapperTest
        extends AbstractMapperTest<ArtifactTag, ArtifactTagMask, ArtifactTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArtifactTag buildSample() {
        return ArtifactTagSamples.build();
    }

}
