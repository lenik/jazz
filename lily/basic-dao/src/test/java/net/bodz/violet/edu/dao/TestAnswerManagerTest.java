package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestAnswer;
import net.bodz.violet.edu.TestAnswerSamples;

public class TestAnswerManagerTest
        extends AbstractManagerTest<TestAnswer, TestAnswerMapper, TestAnswerManager> {

    @Override
    public TestAnswer buildSample()
            throws Exception {
        TestAnswerSamples a = new TestAnswerSamples();
        return a.buildWired(tables);
    }

}
