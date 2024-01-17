package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.ArtifactCategorySamples;

public class ArtifactCategoryMapperTest
        extends AbstractTableTest<ArtifactCategory, ArtifactCategoryMapper> {

    @Override
    public ArtifactCategory buildSample()
            throws Exception {
        ArtifactCategorySamples a = new ArtifactCategorySamples();
        return a.buildWired(tables);
    }

}
