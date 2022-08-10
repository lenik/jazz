package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.plan.DiaryParameter;
import net.bodz.violet.plan.DiaryParameterSamples;

public class DiaryParameterMapperTest
        extends AbstractMapperTest<DiaryParameter, DiaryParameterMask, DiaryParameterMapper> {

    @Override
    public DiaryParameter buildSample() {
        return DiaryParameterSamples.build();
    }

}
