package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractTableTest<TestQuestion, TestQuestionMapper> {

    @Override
    public TestQuestion buildSample()
            throws Exception {
        TestQuestionSamples a = new TestQuestionSamples();
        return a.buildWired(tables);
    }

}
