package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactParameter;
import net.bodz.violet.schema.art.ArtifactParameterSamples;

public class ArtifactParameterManagerTest
        extends AbstractManagerTest<ArtifactParameter, ArtifactParameterMapper, ArtifactParameterManager> {

    @Override
    public ArtifactParameter buildSample()
            throws Exception {
        ArtifactParameterSamples a = new ArtifactParameterSamples();
        return a.buildWired(tables);
    }

}
