package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.art.ArtifactParameter;
import net.bodz.violet.art.ArtifactParameterSamples;

public class ArtifactParameterMapperTest
        extends AbstractMapperTest<ArtifactParameter, ArtifactParameterMask, ArtifactParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArtifactParameter buildSample() {
        return ArtifactParameterSamples.build();
    }

}
