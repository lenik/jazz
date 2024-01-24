package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.Diary;
import net.bodz.violet.schema.plan.DiarySamples;

public class DiaryManagerTest
        extends AbstractManagerTest<Diary, DiaryMapper, DiaryManager> {

    @Override
    public Diary buildSample()
            throws Exception {
        DiarySamples a = new DiarySamples();
        return a.buildWired(tables);
    }

}
