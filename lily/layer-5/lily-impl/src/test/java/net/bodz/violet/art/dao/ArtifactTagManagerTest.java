package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactTag;
import net.bodz.violet.art.ArtifactTagSamples;

public class ArtifactTagManagerTest
        extends AbstractManagerTest<ArtifactTag, ArtifactTagMapper, ArtifactTagManager> {

    @Override
    public ArtifactTag buildSample()
            throws Exception {
        ArtifactTagSamples a = new ArtifactTagSamples();
        return a.buildWired(tables);
    }

}
