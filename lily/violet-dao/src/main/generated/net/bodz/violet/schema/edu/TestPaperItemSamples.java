package net.bodz.violet.schema.edu;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestPaperMapper;
import net.bodz.violet.schema.edu.dao.TestQuestionMapper;

public class TestPaperItemSamples
        extends TestSampleBuilder {

    public TestQuestion question;
    public TestPaper paper;

    @Override
    public TestPaperItem build()
            throws Exception {
        TestPaperItem a = new TestPaperItem();
        a.setQuestion(question);
        a.setPaper(paper);
        a.setId(560394533195265462L);
        a.setScore(new BigDecimal("824"));
        return a;
    }

    @Override
    public TestPaperItemSamples wireAny(IRandomPicker picker) {
        this.question = picker.pickAny(TestQuestionMapper.class, "testq");
        this.paper = picker.pickAny(TestPaperMapper.class, "testpaper");
        return this;
    }

    @Override
    public TestPaperItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
