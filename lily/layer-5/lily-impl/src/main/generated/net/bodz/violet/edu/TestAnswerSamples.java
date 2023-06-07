package net.bodz.violet.edu;

import net.bodz.lily.test.TestSampleBuilder;

public class TestAnswerSamples
        extends TestSampleBuilder {

    public TestQuestion question;

    public TestAnswer build()
            throws Exception {
        TestAnswer a = new TestAnswer();
        a.setQuestion(question);
        a.setId(7247932969513961162L);
        a.setValid(true);
        return a;
    }

}
