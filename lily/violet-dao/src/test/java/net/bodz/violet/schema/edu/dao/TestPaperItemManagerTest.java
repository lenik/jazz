package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestPaperItem;
import net.bodz.violet.schema.edu.TestPaperItemSamples;

public class TestPaperItemManagerTest
        extends AbstractManagerTest<TestPaperItem, TestPaperItemMapper, TestPaperItemManager> {

    @Override
    public TestPaperItem buildSample()
            throws Exception {
        TestPaperItemSamples a = new TestPaperItemSamples();
        return a.buildWired(tables);
    }

}
