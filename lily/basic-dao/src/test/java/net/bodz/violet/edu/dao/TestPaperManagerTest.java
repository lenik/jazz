package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperSamples;

public class TestPaperManagerTest
        extends AbstractManagerTest<TestPaper, TestPaperMapper, TestPaperManager> {

    @Override
    public TestPaper buildSample()
            throws Exception {
        TestPaperSamples a = new TestPaperSamples();
        return a.buildWired(tables);
    }

}
