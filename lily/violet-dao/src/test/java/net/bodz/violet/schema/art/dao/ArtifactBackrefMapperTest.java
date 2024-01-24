package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactBackref;
import net.bodz.violet.schema.art.ArtifactBackrefSamples;

public class ArtifactBackrefMapperTest
        extends AbstractTableTest<ArtifactBackref, ArtifactBackrefMapper> {

    @Override
    public ArtifactBackref buildSample()
            throws Exception {
        ArtifactBackrefSamples a = new ArtifactBackrefSamples();
        return a.buildWired(tables);
    }

}
