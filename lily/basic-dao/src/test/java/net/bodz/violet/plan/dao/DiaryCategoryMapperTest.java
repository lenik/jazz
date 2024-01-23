package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryCategorySamples;

public class DiaryCategoryMapperTest
        extends AbstractTableTest<DiaryCategory, DiaryCategoryMapper> {

    @Override
    public DiaryCategory buildSample()
            throws Exception {
        DiaryCategorySamples a = new DiaryCategorySamples();
        return a.buildWired(tables);
    }

}
