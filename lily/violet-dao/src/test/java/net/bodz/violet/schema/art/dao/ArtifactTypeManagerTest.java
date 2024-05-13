package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactType;
import net.bodz.violet.schema.art.ArtifactTypeSamples;

public class ArtifactTypeManagerTest
        extends AbstractManagerTest<ArtifactType, ArtifactTypeMapper, ArtifactTypeManager> {

    @Override
    public ArtifactType buildSample()
            throws Exception {
        ArtifactTypeSamples a = new ArtifactTypeSamples();
        return a.buildWired(tables);
    }

}
