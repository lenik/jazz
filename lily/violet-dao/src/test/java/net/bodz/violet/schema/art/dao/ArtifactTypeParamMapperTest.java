package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactTypeParam;
import net.bodz.violet.schema.art.ArtifactTypeParamSamples;

public class ArtifactTypeParamMapperTest
        extends AbstractTableTest<ArtifactTypeParam, ArtifactTypeParamMapper> {

    @Override
    public ArtifactTypeParam buildSample()
            throws Exception {
        ArtifactTypeParamSamples a = new ArtifactTypeParamSamples();
        return a.buildWired(tables);
    }

}
