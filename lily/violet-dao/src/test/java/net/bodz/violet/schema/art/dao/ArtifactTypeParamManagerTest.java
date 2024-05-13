package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactTypeParam;
import net.bodz.violet.schema.art.ArtifactTypeParamSamples;

public class ArtifactTypeParamManagerTest
        extends AbstractManagerTest<ArtifactTypeParam, ArtifactTypeParamMapper, ArtifactTypeParamManager> {

    @Override
    public ArtifactTypeParam buildSample()
            throws Exception {
        ArtifactTypeParamSamples a = new ArtifactTypeParamSamples();
        return a.buildWired(tables);
    }

}
