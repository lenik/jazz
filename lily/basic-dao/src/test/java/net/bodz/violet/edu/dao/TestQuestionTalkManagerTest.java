package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestQuestionTalk;
import net.bodz.violet.edu.TestQuestionTalkSamples;

public class TestQuestionTalkManagerTest
        extends AbstractManagerTest<TestQuestionTalk, TestQuestionTalkMapper, TestQuestionTalkManager> {

    @Override
    public TestQuestionTalk buildSample()
            throws Exception {
        TestQuestionTalkSamples a = new TestQuestionTalkSamples();
        return a.buildWired(tables);
    }

}
