package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagMapperTest
        extends AbstractTableTest<TestQuestionTag, TestQuestionTagMask, TestQuestionTagMapper> {

    @Override
    public TestQuestionTag buildSample() {
        return TestQuestionTagSamples.build();
    }

}
