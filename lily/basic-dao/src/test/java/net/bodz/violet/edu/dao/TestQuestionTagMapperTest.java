package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagMapperTest
        extends AbstractTableTest<TestQuestionTag, TestQuestionTagMapper> {

    @Override
    public TestQuestionTag buildSample()
            throws Exception {
        TestQuestionTagSamples a = new TestQuestionTagSamples();
        return a.buildWired(tables);
    }

}
