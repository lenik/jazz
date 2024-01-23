package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactBackref;
import net.bodz.violet.art.ArtifactBackrefSamples;

public class ArtifactBackrefMapperTest
        extends AbstractTableTest<ArtifactBackref, ArtifactBackrefMapper> {

    @Override
    public ArtifactBackref buildSample()
            throws Exception {
        ArtifactBackrefSamples a = new ArtifactBackrefSamples();
        return a.buildWired(tables);
    }

}
