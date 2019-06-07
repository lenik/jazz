package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class TestAnswerSamples
        extends TestSamples {

    public static TestAnswer build(TestQuestion question) {
        TestAnswer a = new TestAnswer();
        a.setLabel("testAnswer-1");
        a.setDescription("A testAnswer named testAnswer-1.");
        a.setQuestion(question);
        return a;
    }

}
