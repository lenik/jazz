package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestApplyItem;
import net.bodz.violet.edu.TestApplyItemSamples;

public class TestApplyItemMapperTest
        extends AbstractMapperTest<TestApplyItem, TestApplyItemMask, TestApplyItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestApplyItem buildSample() {
        return TestApplyItemSamples.build();
    }

}
