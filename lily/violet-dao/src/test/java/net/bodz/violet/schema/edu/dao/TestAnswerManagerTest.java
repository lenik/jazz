package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestAnswer;
import net.bodz.violet.schema.edu.TestAnswerSamples;

public class TestAnswerManagerTest
        extends AbstractManagerTest<TestAnswer, TestAnswerMapper, TestAnswerManager> {

    @Override
    public TestAnswer buildSample()
            throws Exception {
        TestAnswerSamples a = new TestAnswerSamples();
        return a.buildWired(tables);
    }

}
