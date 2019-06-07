package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryCategorySamples;

public class DiaryCategoryMapperTest
        extends AbstractMapperTest<DiaryCategory, DiaryCategoryMask, DiaryCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryCategory buildSample() {
        return DiaryCategorySamples.build();
    }

}
