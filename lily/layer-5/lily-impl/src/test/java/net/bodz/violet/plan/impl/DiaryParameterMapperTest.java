package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.DiaryParameter;
import net.bodz.violet.plan.DiaryParameterSamples;

public class DiaryParameterMapperTest
        extends AbstractMapperTest<DiaryParameter, DiaryParameterMask, DiaryParameterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public DiaryParameter buildSample() {
        return DiaryParameterSamples.build();
    }

}
