package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryParameterType;
import net.bodz.violet.schema.plan.DiaryParameterTypeSamples;

public class DiaryParameterTypeMapperTest
        extends AbstractTableTest<DiaryParameterType, DiaryParameterTypeMapper> {

    @Override
    public DiaryParameterType buildSample()
            throws Exception {
        DiaryParameterTypeSamples a = new DiaryParameterTypeSamples();
        return a.buildWired(tables);
    }

}
