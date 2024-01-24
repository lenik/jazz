package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryParameter;
import net.bodz.violet.schema.plan.DiaryParameterSamples;

public class DiaryParameterMapperTest
        extends AbstractTableTest<DiaryParameter, DiaryParameterMapper> {

    @Override
    public DiaryParameter buildSample()
            throws Exception {
        DiaryParameterSamples a = new DiaryParameterSamples();
        return a.buildWired(tables);
    }

}
