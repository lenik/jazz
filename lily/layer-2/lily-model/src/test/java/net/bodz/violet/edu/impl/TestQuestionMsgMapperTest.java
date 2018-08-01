package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestionMsg;
import net.bodz.violet.edu.TestQuestionMsgSamples;

public class TestQuestionMsgMapperTest
        extends AbstractMapperTest<TestQuestionMsg, TestQuestionMsgMask, TestQuestionMsgMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestionMsg buildSample() {
        return TestQuestionMsgSamples.build();
    }

}
