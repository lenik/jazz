package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestQuestionFav;
import net.bodz.violet.schema.edu.TestQuestionFavSamples;

public class TestQuestionFavMapperTest
        extends AbstractTableTest<TestQuestionFav, TestQuestionFavMapper> {

    @Override
    public TestQuestionFav buildSample()
            throws Exception {
        TestQuestionFavSamples a = new TestQuestionFavSamples();
        return a.buildWired(tables);
    }

}
