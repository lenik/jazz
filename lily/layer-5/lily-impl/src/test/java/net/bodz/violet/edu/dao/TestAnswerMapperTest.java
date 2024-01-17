package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestAnswer;
import net.bodz.violet.edu.TestAnswerSamples;

public class TestAnswerMapperTest
        extends AbstractTableTest<TestAnswer, TestAnswerMapper> {

    @Override
    public TestAnswer buildSample()
            throws Exception {
        TestAnswerSamples a = new TestAnswerSamples();
        return a.buildWired(tables);
    }

}
