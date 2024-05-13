package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryParameterType;
import net.bodz.violet.schema.plan.DiaryParameterTypeSamples;

public class DiaryParameterTypeManagerTest
        extends AbstractManagerTest<DiaryParameterType, DiaryParameterTypeMapper, DiaryParameterTypeManager> {

    @Override
    public DiaryParameterType buildSample()
            throws Exception {
        DiaryParameterTypeSamples a = new DiaryParameterTypeSamples();
        return a.buildWired(tables);
    }

}
