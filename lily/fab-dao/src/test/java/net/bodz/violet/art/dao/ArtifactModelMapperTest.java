package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.ArtifactModelSamples;

public class ArtifactModelMapperTest
        extends AbstractTableTest<ArtifactModel, ArtifactModelMapper> {

    @Override
    public ArtifactModel buildSample()
            throws Exception {
        ArtifactModelSamples a = new ArtifactModelSamples();
        return a.buildWired(tables);
    }

}
