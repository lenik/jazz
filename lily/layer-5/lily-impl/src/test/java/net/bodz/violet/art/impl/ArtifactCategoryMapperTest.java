package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.ArtifactCategorySamples;

public class ArtifactCategoryMapperTest
        extends AbstractMapperTest<ArtifactCategory, ArtifactCategoryMask, ArtifactCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArtifactCategory buildSample() {
        return ArtifactCategorySamples.build();
    }

}
