package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryCategory;
import net.bodz.violet.schema.plan.DiaryCategorySamples;

public class DiaryCategoryManagerTest
        extends AbstractManagerTest<DiaryCategory, DiaryCategoryMapper, DiaryCategoryManager> {

    @Override
    public DiaryCategory buildSample()
            throws Exception {
        DiaryCategorySamples a = new DiaryCategorySamples();
        return a.buildWired(tables);
    }

}
