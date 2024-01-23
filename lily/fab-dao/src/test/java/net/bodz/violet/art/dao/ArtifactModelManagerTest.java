package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.ArtifactModelSamples;

public class ArtifactModelManagerTest
        extends AbstractManagerTest<ArtifactModel, ArtifactModelMapper, ArtifactModelManager> {

    @Override
    public ArtifactModel buildSample()
            throws Exception {
        ArtifactModelSamples a = new ArtifactModelSamples();
        return a.buildWired(tables);
    }

}
