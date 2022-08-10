package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.TestAnswer;
import net.bodz.violet.edu.TestAnswerSamples;
import net.bodz.violet.edu.TestQuestion;

public class TestAnswerMapperTest
        extends AbstractMapperTest<TestAnswer, TestAnswerMask, TestAnswerMapper> {

    @Override
    public TestAnswer buildSample() {
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        return TestAnswerSamples.build(question);
    }

}
