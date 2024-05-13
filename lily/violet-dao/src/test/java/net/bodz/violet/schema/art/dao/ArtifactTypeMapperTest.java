package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactType;
import net.bodz.violet.schema.art.ArtifactTypeSamples;

public class ArtifactTypeMapperTest
        extends AbstractTableTest<ArtifactType, ArtifactTypeMapper> {

    @Override
    public ArtifactType buildSample()
            throws Exception {
        ArtifactTypeSamples a = new ArtifactTypeSamples();
        return a.buildWired(tables);
    }

}
