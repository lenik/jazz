package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiarySamples;

public class DiaryManagerTest
        extends AbstractManagerTest<Diary, DiaryMapper, DiaryManager> {

    @Override
    public Diary buildSample()
            throws Exception {
        DiarySamples a = new DiarySamples();
        return a.buildWired(tables);
    }

}
