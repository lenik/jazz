package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplyItem;
import net.bodz.violet.edu.TestApplyItemSamples;
import net.bodz.violet.edu.TestQuestion;

public class TestApplyItemMapperTest
        extends AbstractMapperTest<TestApplyItem, TestApplyItemMask, TestApplyItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestApplyItem buildSample() {
        TestApply apply = tables.pickAny(TestApplyMapper.class, "testapply");
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        return TestApplyItemSamples.build(apply, question);
    }

}
