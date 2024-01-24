package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.ArtifactSamples;

public class ArtifactMapperTest
        extends AbstractTableTest<Artifact, ArtifactMapper> {

    @Override
    public Artifact buildSample()
            throws Exception {
        ArtifactSamples a = new ArtifactSamples();
        return a.buildWired(tables);
    }

}
