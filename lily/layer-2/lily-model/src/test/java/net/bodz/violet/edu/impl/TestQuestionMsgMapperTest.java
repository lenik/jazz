package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionMsg;
import net.bodz.violet.edu.TestQuestionMsgSamples;

public class TestQuestionMsgMapperTest
        extends AbstractMapperTest<TestQuestionMsg, TestQuestionMsgMask, TestQuestionMsgMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestionMsg buildSample() {
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        User op = tables.pickAny(UserMapper.class, "user");
        return TestQuestionMsgSamples.build(question, op);
    }

}
