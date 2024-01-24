package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryCategory;
import net.bodz.violet.schema.plan.DiaryCategorySamples;

public class DiaryCategoryMapperTest
        extends AbstractTableTest<DiaryCategory, DiaryCategoryMapper> {

    @Override
    public DiaryCategory buildSample()
            throws Exception {
        DiaryCategorySamples a = new DiaryCategorySamples();
        return a.buildWired(tables);
    }

}
