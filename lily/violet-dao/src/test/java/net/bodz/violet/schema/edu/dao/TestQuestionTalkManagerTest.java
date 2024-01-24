package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestionTalk;
import net.bodz.violet.schema.edu.TestQuestionTalkSamples;

public class TestQuestionTalkManagerTest
        extends AbstractManagerTest<TestQuestionTalk, TestQuestionTalkMapper, TestQuestionTalkManager> {

    @Override
    public TestQuestionTalk buildSample()
            throws Exception {
        TestQuestionTalkSamples a = new TestQuestionTalkSamples();
        return a.buildWired(tables);
    }

}
