package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactParameter;
import net.bodz.violet.schema.art.ArtifactParameterSamples;

public class ArtifactParameterMapperTest
        extends AbstractTableTest<ArtifactParameter, ArtifactParameterMapper> {

    @Override
    public ArtifactParameter buildSample()
            throws Exception {
        ArtifactParameterSamples a = new ArtifactParameterSamples();
        return a.buildWired(tables);
    }

}
