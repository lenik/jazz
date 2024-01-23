package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryTag;
import net.bodz.violet.plan.DiaryTagSamples;

public class DiaryTagManagerTest
        extends AbstractManagerTest<DiaryTag, DiaryTagMapper, DiaryTagManager> {

    @Override
    public DiaryTag buildSample()
            throws Exception {
        DiaryTagSamples a = new DiaryTagSamples();
        return a.buildWired(tables);
    }

}
