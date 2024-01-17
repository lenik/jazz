package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagManagerTest
        extends AbstractManagerTest<TestQuestionTag, TestQuestionTagMapper, TestQuestionTagManager> {

    @Override
    public TestQuestionTag buildSample()
            throws Exception {
        TestQuestionTagSamples a = new TestQuestionTagSamples();
        return a.buildWired(tables);
    }

}
