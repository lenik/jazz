package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestApplyItem;
import net.bodz.violet.schema.edu.TestApplyItemSamples;

public class TestApplyItemMapperTest
        extends AbstractTableTest<TestApplyItem, TestApplyItemMapper> {

    @Override
    public TestApplyItem buildSample()
            throws Exception {
        TestApplyItemSamples a = new TestApplyItemSamples();
        return a.buildWired(tables);
    }

}
