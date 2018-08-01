package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;

public class TestApplyMapperTest
        extends AbstractMapperTest<TestApply, TestApplyMask, TestApplyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestApply buildSample() {
        return TestApplySamples.build();
    }

}
