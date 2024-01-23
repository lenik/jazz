package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionManagerTest
        extends AbstractManagerTest<TestQuestion, TestQuestionMapper, TestQuestionManager> {

    @Override
    public TestQuestion buildSample()
            throws Exception {
        TestQuestionSamples a = new TestQuestionSamples();
        return a.buildWired(tables);
    }

}
