package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionTalk;
import net.bodz.violet.edu.TestQuestionTalkSamples;

public class TestQuestionTalkMapperTest
        extends AbstractMapperTest<TestQuestionTalk, TestQuestionTalkMask, TestQuestionTalkMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestQuestionTalk buildSample() {
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        User op = tables.pickAny(UserMapper.class, "user");
        return TestQuestionTalkSamples.build(question, op);
    }

}
