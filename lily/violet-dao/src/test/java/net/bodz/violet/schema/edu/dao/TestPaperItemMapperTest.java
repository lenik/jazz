package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestPaperItem;
import net.bodz.violet.schema.edu.TestPaperItemSamples;

public class TestPaperItemMapperTest
        extends AbstractTableTest<TestPaperItem, TestPaperItemMapper> {

    @Override
    public TestPaperItem buildSample()
            throws Exception {
        TestPaperItemSamples a = new TestPaperItemSamples();
        return a.buildWired(tables);
    }

}
