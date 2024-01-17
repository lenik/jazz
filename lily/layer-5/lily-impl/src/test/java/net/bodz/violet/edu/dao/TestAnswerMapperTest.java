package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestAnswer;
import net.bodz.violet.edu.TestAnswerSamples;

public class TestAnswerMapperTest
        extends AbstractTableTest<TestAnswer, TestAnswerCriteriaBuilder, TestAnswerMapper> {

    @Override
    public TestAnswer buildSample()
            throws Exception {
        TestAnswerSamples a = new TestAnswerSamples();
        a.question = tables.pickAny(TestQuestionMapper.class, "testq");
        return a.build();
    }

}
