package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestPaperItem;
import net.bodz.violet.edu.TestPaperItemSamples;

public class TestPaperItemMapperTest
        extends AbstractMapperTest<TestPaperItem, TestPaperItemMask, TestPaperItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestPaperItem buildSample() {
        return TestPaperItemSamples.build();
    }

}
