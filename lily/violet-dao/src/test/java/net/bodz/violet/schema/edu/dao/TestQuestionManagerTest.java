package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestion;
import net.bodz.violet.schema.edu.TestQuestionSamples;

public class TestQuestionManagerTest
        extends AbstractManagerTest<TestQuestion, TestQuestionMapper, TestQuestionManager> {

    @Override
    public TestQuestion buildSample()
            throws Exception {
        TestQuestionSamples a = new TestQuestionSamples();
        return a.buildWired(tables);
    }

}
