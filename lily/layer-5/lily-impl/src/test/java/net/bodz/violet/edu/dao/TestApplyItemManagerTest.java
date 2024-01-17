package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestApplyItem;
import net.bodz.violet.edu.TestApplyItemSamples;

public class TestApplyItemManagerTest
        extends AbstractManagerTest<TestApplyItem, TestApplyItemMapper, TestApplyItemManager> {

    @Override
    public TestApplyItem buildSample()
            throws Exception {
        TestApplyItemSamples a = new TestApplyItemSamples();
        return a.buildWired(tables);
    }

}
