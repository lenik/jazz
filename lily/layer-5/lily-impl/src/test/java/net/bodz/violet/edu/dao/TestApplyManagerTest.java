package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;

public class TestApplyManagerTest
        extends AbstractManagerTest<TestApply, TestApplyMapper, TestApplyManager> {

    @Override
    public TestApply buildSample()
            throws Exception {
        TestApplySamples a = new TestApplySamples();
        return a.buildWired(tables);
    }

}
