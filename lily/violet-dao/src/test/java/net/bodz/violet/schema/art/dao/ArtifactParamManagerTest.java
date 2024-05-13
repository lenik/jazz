package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactParam;
import net.bodz.violet.schema.art.ArtifactParamSamples;

public class ArtifactParamManagerTest
        extends AbstractManagerTest<ArtifactParam, ArtifactParamMapper, ArtifactParamManager> {

    @Override
    public ArtifactParam buildSample()
            throws Exception {
        ArtifactParamSamples a = new ArtifactParamSamples();
        return a.buildWired(tables);
    }

}
