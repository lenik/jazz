package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestionFav;
import net.bodz.violet.edu.TestQuestionFavSamples;

public class TestQuestionFavMapperTest
        extends AbstractMapperTest<TestQuestionFav, TestQuestionFavMask, TestQuestionFavMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestionFav buildSample() {
        return TestQuestionFavSamples.build();
    }

}
