package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiarySamples;

public class DiaryMapperTest
        extends AbstractTableTest<Diary, DiaryMapper> {

    @Override
    public Diary buildSample()
            throws Exception {
        DiarySamples a = new DiarySamples();
        return a.buildWired(tables);
    }

}
