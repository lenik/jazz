package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestQuestion;
import net.bodz.violet.schema.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractTableTest<TestQuestion, TestQuestionMapper> {

    @Override
    public TestQuestion buildSample()
            throws Exception {
        TestQuestionSamples a = new TestQuestionSamples();
        return a.buildWired(tables);
    }

}
