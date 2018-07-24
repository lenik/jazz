package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractMapperTest<TestQuestion, TestQuestionMask, TestQuestionMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestion buildSample() {
        return TestQuestionSamples.build();
    }

}
