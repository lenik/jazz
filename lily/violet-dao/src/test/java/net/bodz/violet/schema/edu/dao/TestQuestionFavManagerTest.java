package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestionFav;
import net.bodz.violet.schema.edu.TestQuestionFavSamples;

public class TestQuestionFavManagerTest
        extends AbstractManagerTest<TestQuestionFav, TestQuestionFavMapper, TestQuestionFavManager> {

    @Override
    public TestQuestionFav buildSample()
            throws Exception {
        TestQuestionFavSamples a = new TestQuestionFavSamples();
        return a.buildWired(tables);
    }

}
