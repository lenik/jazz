package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagMapperTest
        extends AbstractMapperTest<TestQuestionTag, TestQuestionTagMask, TestQuestionTagMapper> {

    @Override
    public TestQuestionTag buildSample() {
        return TestQuestionTagSamples.build();
    }

}
