package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactBackref;
import net.bodz.violet.schema.art.ArtifactBackrefSamples;

public class ArtifactBackrefManagerTest
        extends AbstractManagerTest<ArtifactBackref, ArtifactBackrefMapper, ArtifactBackrefManager> {

    @Override
    public ArtifactBackref buildSample()
            throws Exception {
        ArtifactBackrefSamples a = new ArtifactBackrefSamples();
        return a.buildWired(tables);
    }

}
