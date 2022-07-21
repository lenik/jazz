package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagMapperTest
        extends AbstractMapperTest<TestQuestionTag, TestQuestionTagMask, TestQuestionTagMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestQuestionTag buildSample() {
        return TestQuestionTagSamples.build();
    }

}
