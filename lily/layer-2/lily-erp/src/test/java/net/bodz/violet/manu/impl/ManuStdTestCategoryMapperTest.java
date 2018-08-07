package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdTestCategory;
import net.bodz.violet.manu.ManuStdTestCategorySamples;

public class ManuStdTestCategoryMapperTest
        extends AbstractMapperTest<ManuStdTestCategory, ManuStdTestCategoryMask, ManuStdTestCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdTestCategory buildSample() {
        return ManuStdTestCategorySamples.build();
    }

}
