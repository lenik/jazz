package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactParameter;
import net.bodz.violet.art.ArtifactParameterSamples;

public class ArtifactParameterMapperTest
        extends AbstractTableTest<ArtifactParameter, ArtifactParameterMapper> {

    @Override
    public ArtifactParameter buildSample()
            throws Exception {
        ArtifactParameterSamples a = new ArtifactParameterSamples();
        return a.buildWired(tables);
    }

}
