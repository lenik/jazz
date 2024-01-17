package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestPaperItem;
import net.bodz.violet.edu.TestPaperItemSamples;

public class TestPaperItemMapperTest
        extends AbstractTableTest<TestPaperItem, TestPaperItemCriteriaBuilder, TestPaperItemMapper> {

    @Override
    public TestPaperItem buildSample()
            throws Exception {
        TestPaperItemSamples a = new TestPaperItemSamples();
        a.question = tables.pickAny(TestQuestionMapper.class, "testq");
        a.paper = tables.pickAny(TestPaperMapper.class, "testpaper");
        return a.build();
    }

}
