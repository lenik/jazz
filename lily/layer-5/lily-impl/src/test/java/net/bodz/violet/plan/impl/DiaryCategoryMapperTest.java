package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryCategorySamples;

public class DiaryCategoryMapperTest
        extends AbstractTableTest<DiaryCategory, DiaryCategoryMask, DiaryCategoryMapper> {

    @Override
    public DiaryCategory buildSample() {
        return DiaryCategorySamples.build();
    }

}
