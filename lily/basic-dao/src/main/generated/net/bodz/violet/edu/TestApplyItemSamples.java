package net.bodz.violet.edu;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.TestApplyMapper;
import net.bodz.violet.edu.dao.TestQuestionMapper;

public class TestApplyItemSamples
        extends TestSampleBuilder {

    public TestQuestion question;
    public TestApply apply;

    @Override
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

    @Override
    public TestApplyItemSamples wireAny(IRandomPicker picker) {
        this.question = picker.pickAny(TestQuestionMapper.class, "testq");
        this.apply = picker.pickAny(TestApplyMapper.class, "testapply");
        return this;
    }

    @Override
    public TestApplyItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
