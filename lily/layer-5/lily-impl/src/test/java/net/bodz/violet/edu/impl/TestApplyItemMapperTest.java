package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplyItem;
import net.bodz.violet.edu.TestApplyItemSamples;
import net.bodz.violet.edu.TestQuestion;

public class TestApplyItemMapperTest
        extends AbstractTableTest<TestApplyItem, TestApplyItemMask, TestApplyItemMapper> {

    @Override
    public TestApplyItem buildSample() {
        TestApply apply = tables.pickAny(TestApplyMapper.class, "testapply");
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        return TestApplyItemSamples.build(apply, question);
    }

}
