package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestApplyItem;
import net.bodz.violet.edu.TestApplyItemSamples;

public class TestApplyItemMapperTest
        extends AbstractTableTest<TestApplyItem, TestApplyItemMapper> {

    @Override
    public TestApplyItem buildSample()
            throws Exception {
        TestApplyItemSamples a = new TestApplyItemSamples();
        return a.buildWired(tables);
    }

}
