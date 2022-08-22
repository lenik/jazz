package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactParameter;
import net.bodz.violet.art.ArtifactParameterSamples;

public class ArtifactParameterMapperTest
        extends AbstractTableTest<ArtifactParameter, ArtifactParameterMask, ArtifactParameterMapper> {

    @Override
    public ArtifactParameter buildSample() {
        return ArtifactParameterSamples.build();
    }

}
