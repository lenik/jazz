package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactParameter;
import net.bodz.violet.art.ArtifactParameterSamples;

public class ArtifactParameterManagerTest
        extends AbstractManagerTest<ArtifactParameter, ArtifactParameterMapper, ArtifactParameterManager> {

    @Override
    public ArtifactParameter buildSample()
            throws Exception {
        ArtifactParameterSamples a = new ArtifactParameterSamples();
        return a.buildWired(tables);
    }

}
