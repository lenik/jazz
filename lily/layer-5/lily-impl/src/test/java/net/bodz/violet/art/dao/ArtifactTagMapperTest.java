package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactTag;
import net.bodz.violet.art.ArtifactTagSamples;

public class ArtifactTagMapperTest
        extends AbstractTableTest<ArtifactTag, ArtifactTagMapper> {

    @Override
    public ArtifactTag buildSample()
            throws Exception {
        ArtifactTagSamples a = new ArtifactTagSamples();
        return a.buildWired(tables);
    }

}
