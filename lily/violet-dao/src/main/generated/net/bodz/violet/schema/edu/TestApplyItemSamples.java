package net.bodz.violet.schema.edu;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestApplyMapper;
import net.bodz.violet.schema.edu.dao.TestQuestionMapper;

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
        a.setAnswer(640629080);
        a.setAnstext("Ran ixhkbp");
        a.setScore(0.8918631298186213);
        a.setWaittime(0.5819204566094787);
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
