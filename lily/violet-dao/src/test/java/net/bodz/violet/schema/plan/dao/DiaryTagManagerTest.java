package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryTag;
import net.bodz.violet.schema.plan.DiaryTagSamples;

public class DiaryTagManagerTest
        extends AbstractManagerTest<DiaryTag, DiaryTagMapper, DiaryTagManager> {

    @Override
    public DiaryTag buildSample()
            throws Exception {
        DiaryTagSamples a = new DiaryTagSamples();
        return a.buildWired(tables);
    }

}
