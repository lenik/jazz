package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestPaperItem;
import net.bodz.violet.edu.TestPaperItemSamples;

public class TestPaperItemManagerTest
        extends AbstractManagerTest<TestPaperItem, TestPaperItemMapper, TestPaperItemManager> {

    @Override
    public TestPaperItem buildSample()
            throws Exception {
        TestPaperItemSamples a = new TestPaperItemSamples();
        return a.buildWired(tables);
    }

}
