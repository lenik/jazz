package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactCategory;
import net.bodz.violet.schema.art.ArtifactCategorySamples;

public class ArtifactCategoryManagerTest
        extends AbstractManagerTest<ArtifactCategory, ArtifactCategoryMapper, ArtifactCategoryManager> {

    @Override
    public ArtifactCategory buildSample()
            throws Exception {
        ArtifactCategorySamples a = new ArtifactCategorySamples();
        return a.buildWired(tables);
    }

}
