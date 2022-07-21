package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.ArtifactBackref;
import net.bodz.violet.art.ArtifactBackrefSamples;

public class ArtifactBackrefMapperTest
        extends AbstractMapperTest<ArtifactBackref, ArtifactBackrefMask, ArtifactBackrefMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArtifactBackref buildSample() {
        return ArtifactBackrefSamples.build();
    }

}
