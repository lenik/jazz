package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestionTag;
import net.bodz.violet.schema.edu.TestQuestionTagSamples;

public class TestQuestionTagManagerTest
        extends AbstractManagerTest<TestQuestionTag, TestQuestionTagMapper, TestQuestionTagManager> {

    @Override
    public TestQuestionTag buildSample()
            throws Exception {
        TestQuestionTagSamples a = new TestQuestionTagSamples();
        return a.buildWired(tables);
    }

}
