package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.Diary;
import net.bodz.violet.schema.plan.DiarySamples;

public class DiaryMapperTest
        extends AbstractTableTest<Diary, DiaryMapper> {

    @Override
    public Diary buildSample()
            throws Exception {
        DiarySamples a = new DiarySamples();
        return a.buildWired(tables);
    }

}
