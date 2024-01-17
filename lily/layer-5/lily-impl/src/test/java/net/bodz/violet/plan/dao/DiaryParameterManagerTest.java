package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryParameter;
import net.bodz.violet.plan.DiaryParameterSamples;

public class DiaryParameterManagerTest
        extends AbstractManagerTest<DiaryParameter, DiaryParameterMapper, DiaryParameterManager> {

    @Override
    public DiaryParameter buildSample()
            throws Exception {
        DiaryParameterSamples a = new DiaryParameterSamples();
        return a.buildWired(tables);
    }

}
