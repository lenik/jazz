package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperItem;
import net.bodz.violet.edu.TestPaperItemSamples;
import net.bodz.violet.edu.TestQuestion;

public class TestPaperItemMapperTest
        extends AbstractMapperTest<TestPaperItem, TestPaperItemMask, TestPaperItemMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestPaperItem buildSample() {
        TestPaper paper = tables.pickAny(TestPaperMapper.class, "testpaper");
        TestQuestion question = tables.pickAny(TestQuestionMapper.class, "testq");
        return TestPaperItemSamples.build(paper, question);
    }

}
