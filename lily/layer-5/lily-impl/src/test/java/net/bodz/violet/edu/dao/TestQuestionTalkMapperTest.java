package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTalk;
import net.bodz.violet.edu.TestQuestionTalkSamples;

public class TestQuestionTalkMapperTest
        extends AbstractTableTest<TestQuestionTalk, TestQuestionTalkMapper> {

    @Override
    public TestQuestionTalk buildSample()
            throws Exception {
        TestQuestionTalkSamples a = new TestQuestionTalkSamples();
        return a.buildWired(tables);
    }

}
