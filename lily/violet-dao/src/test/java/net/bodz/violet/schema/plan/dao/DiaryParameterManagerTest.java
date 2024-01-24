package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryParameter;
import net.bodz.violet.schema.plan.DiaryParameterSamples;

public class DiaryParameterManagerTest
        extends AbstractManagerTest<DiaryParameter, DiaryParameterMapper, DiaryParameterManager> {

    @Override
    public DiaryParameter buildSample()
            throws Exception {
        DiaryParameterSamples a = new DiaryParameterSamples();
        return a.buildWired(tables);
    }

}
