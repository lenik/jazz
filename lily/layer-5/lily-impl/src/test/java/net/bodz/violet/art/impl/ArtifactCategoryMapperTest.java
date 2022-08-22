package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.ArtifactCategorySamples;

public class ArtifactCategoryMapperTest
        extends AbstractTableTest<ArtifactCategory, ArtifactCategoryMask, ArtifactCategoryMapper> {

    @Override
    public ArtifactCategory buildSample() {
        return ArtifactCategorySamples.build();
    }

}
