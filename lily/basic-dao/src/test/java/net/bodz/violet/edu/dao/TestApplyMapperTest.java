package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;

public class TestApplyMapperTest
        extends AbstractTableTest<TestApply, TestApplyMapper> {

    @Override
    public TestApply buildSample()
            throws Exception {
        TestApplySamples a = new TestApplySamples();
        return a.buildWired(tables);
    }

}
