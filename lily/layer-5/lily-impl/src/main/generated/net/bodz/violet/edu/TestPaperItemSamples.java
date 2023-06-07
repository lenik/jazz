package net.bodz.violet.edu;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;

public class TestPaperItemSamples
        extends TestSampleBuilder {

    public TestQuestion question;
    public TestPaper paper;

    public TestPaperItem build()
            throws Exception {
        TestPaperItem a = new TestPaperItem();
        a.setQuestion(question);
        a.setPaper(paper);
        a.setId(560394533195265462L);
        a.setScore(new BigDecimal("824"));
        return a;
    }

}
