package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.ArtifactCategorySamples;

public class ArtifactCategoryManagerTest
        extends AbstractManagerTest<ArtifactCategory, ArtifactCategoryMapper, ArtifactCategoryManager> {

    @Override
    public ArtifactCategory buildSample()
            throws Exception {
        ArtifactCategorySamples a = new ArtifactCategorySamples();
        return a.buildWired(tables);
    }

}
