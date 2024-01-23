package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryCategorySamples;

public class DiaryCategoryManagerTest
        extends AbstractManagerTest<DiaryCategory, DiaryCategoryMapper, DiaryCategoryManager> {

    @Override
    public DiaryCategory buildSample()
            throws Exception {
        DiaryCategorySamples a = new DiaryCategorySamples();
        return a.buildWired(tables);
    }

}
