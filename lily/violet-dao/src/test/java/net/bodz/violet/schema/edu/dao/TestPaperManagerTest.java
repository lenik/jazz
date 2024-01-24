package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestPaper;
import net.bodz.violet.schema.edu.TestPaperSamples;

public class TestPaperManagerTest
        extends AbstractManagerTest<TestPaper, TestPaperMapper, TestPaperManager> {

    @Override
    public TestPaper buildSample()
            throws Exception {
        TestPaperSamples a = new TestPaperSamples();
        return a.buildWired(tables);
    }

}
