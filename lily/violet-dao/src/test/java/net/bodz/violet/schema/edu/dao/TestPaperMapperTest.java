package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestPaper;
import net.bodz.violet.schema.edu.TestPaperSamples;

public class TestPaperMapperTest
        extends AbstractTableTest<TestPaper, TestPaperMapper> {

    @Override
    public TestPaper buildSample()
            throws Exception {
        TestPaperSamples a = new TestPaperSamples();
        return a.buildWired(tables);
    }

}
