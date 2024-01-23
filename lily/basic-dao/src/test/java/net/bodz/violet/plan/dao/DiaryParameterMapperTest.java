package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryParameter;
import net.bodz.violet.plan.DiaryParameterSamples;

public class DiaryParameterMapperTest
        extends AbstractTableTest<DiaryParameter, DiaryParameterMapper> {

    @Override
    public DiaryParameter buildSample()
            throws Exception {
        DiaryParameterSamples a = new DiaryParameterSamples();
        return a.buildWired(tables);
    }

}
