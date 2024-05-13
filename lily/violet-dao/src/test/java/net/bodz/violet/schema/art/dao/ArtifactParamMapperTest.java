package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactParam;
import net.bodz.violet.schema.art.ArtifactParamSamples;

public class ArtifactParamMapperTest
        extends AbstractTableTest<ArtifactParam, ArtifactParamMapper> {

    @Override
    public ArtifactParam buildSample()
            throws Exception {
        ArtifactParamSamples a = new ArtifactParamSamples();
        return a.buildWired(tables);
    }

}
