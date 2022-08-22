package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionFav;
import net.bodz.violet.edu.TestQuestionFavSamples;

public class TestQuestionFavMapperTest
        extends AbstractTableTest<TestQuestionFav, TestQuestionFavMask, TestQuestionFavMapper> {

    @Override
    public TestQuestionFav buildSample() {
        return TestQuestionFavSamples.build();
    }

}
