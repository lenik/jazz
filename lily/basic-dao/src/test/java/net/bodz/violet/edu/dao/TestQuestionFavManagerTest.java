package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestQuestionFav;
import net.bodz.violet.edu.TestQuestionFavSamples;

public class TestQuestionFavManagerTest
        extends AbstractManagerTest<TestQuestionFav, TestQuestionFavMapper, TestQuestionFavManager> {

    @Override
    public TestQuestionFav buildSample()
            throws Exception {
        TestQuestionFavSamples a = new TestQuestionFavSamples();
        return a.buildWired(tables);
    }

}
