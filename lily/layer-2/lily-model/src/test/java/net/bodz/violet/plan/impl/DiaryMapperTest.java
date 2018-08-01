package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiarySamples;

public class DiaryMapperTest
        extends AbstractMapperTest<Diary, DiaryMask, DiaryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Diary buildSample() {
        return DiarySamples.build();
    }

}
