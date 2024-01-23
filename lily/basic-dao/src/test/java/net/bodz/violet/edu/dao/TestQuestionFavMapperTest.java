package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionFav;
import net.bodz.violet.edu.TestQuestionFavSamples;

public class TestQuestionFavMapperTest
        extends AbstractTableTest<TestQuestionFav, TestQuestionFavMapper> {

    @Override
    public TestQuestionFav buildSample()
            throws Exception {
        TestQuestionFavSamples a = new TestQuestionFavSamples();
        return a.buildWired(tables);
    }

}
