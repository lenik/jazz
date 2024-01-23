package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperSamples;

public class TestPaperMapperTest
        extends AbstractTableTest<TestPaper, TestPaperMapper> {

    @Override
    public TestPaper buildSample()
            throws Exception {
        TestPaperSamples a = new TestPaperSamples();
        return a.buildWired(tables);
    }

}
