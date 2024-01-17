package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryTag;
import net.bodz.violet.plan.DiaryTagSamples;

public class DiaryTagMapperTest
        extends AbstractTableTest<DiaryTag, DiaryTagMapper> {

    @Override
    public DiaryTag buildSample()
            throws Exception {
        DiaryTagSamples a = new DiaryTagSamples();
        return a.buildWired(tables);
    }

}
