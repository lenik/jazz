package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactTag;
import net.bodz.violet.schema.art.ArtifactTagSamples;

public class ArtifactTagMapperTest
        extends AbstractTableTest<ArtifactTag, ArtifactTagMapper> {

    @Override
    public ArtifactTag buildSample()
            throws Exception {
        ArtifactTagSamples a = new ArtifactTagSamples();
        return a.buildWired(tables);
    }

}
