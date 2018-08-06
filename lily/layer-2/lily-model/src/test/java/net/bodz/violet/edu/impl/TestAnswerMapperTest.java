package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestAnswer;
import net.bodz.violet.edu.TestAnswerSamples;
import net.bodz.violet.edu.TestQuestion;

public class TestAnswerMapperTest
        extends AbstractMapperTest<TestAnswer, TestAnswerMask, TestAnswerMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestAnswer buildSample() {
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        return TestAnswerSamples.build(question);
    }

}
