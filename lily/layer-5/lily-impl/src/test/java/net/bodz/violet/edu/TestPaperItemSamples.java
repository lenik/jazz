package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class TestPaperItemSamples
        extends TestSamples {

    public static TestPaperItem build(TestPaper paper, TestQuestion question) {
        TestPaperItem a = new TestPaperItem();
        a.setLabel("testPaperItem-1");
        a.setDescription("A testPaperItem named testPaperItem-1.");
        a.setPaper(paper);
        a.setQuestion(question);
        return a;
    }

}
