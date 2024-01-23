package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactBackref;
import net.bodz.violet.art.ArtifactBackrefSamples;

public class ArtifactBackrefManagerTest
        extends AbstractManagerTest<ArtifactBackref, ArtifactBackrefMapper, ArtifactBackrefManager> {

    @Override
    public ArtifactBackref buildSample()
            throws Exception {
        ArtifactBackrefSamples a = new ArtifactBackrefSamples();
        return a.buildWired(tables);
    }

}
