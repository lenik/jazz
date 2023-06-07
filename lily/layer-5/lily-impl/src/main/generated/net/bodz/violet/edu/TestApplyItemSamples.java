package net.bodz.violet.edu;

import net.bodz.lily.test.TestSampleBuilder;

public class TestApplyItemSamples
        extends TestSampleBuilder {

    public TestQuestion question;
    public TestApply apply;

    public TestApplyItem build()
            throws Exception {
        TestApplyItem a = new TestApplyItem();
        a.setQuestion(question);
        a.setApply(apply);
        a.setId(2751480947002123332L);
        a.setAnswer(2085688517);
        a.setAnstext("Ran ixhkbp");
        a.setScore(0.5819204566094787);
        a.setWaittime(0.3935042714013969);
        return a;
    }

}
