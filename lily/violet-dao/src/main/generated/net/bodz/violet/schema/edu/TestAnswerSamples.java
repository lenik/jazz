package net.bodz.violet.schema.edu;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestQuestionMapper;

public class TestAnswerSamples
        extends TestSampleBuilder {

    public TestQuestion question;

    @Override
    public TestAnswer build()
            throws Exception {
        TestAnswer a = new TestAnswer();
        a.setQuestion(question);
        a.setId(7247932969513961162L);
        a.setValid(true);
        return a;
    }

    @Override
    public TestAnswerSamples wireAny(IRandomPicker picker) {
        this.question = picker.pickAny(TestQuestionMapper.class, "testq");
        return this;
    }

    @Override
    public TestAnswer buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
