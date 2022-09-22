package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class TestQuestionTalkSamples
        extends TestSamples {

    public static TestQuestionTalk build(TestQuestion q, User op) {
        TestQuestionTalk a = new TestQuestionTalk();
        a.setSubject("testQuestionMsg-1");
        a.setRawText("A testQuestionMsg named testQuestionMsg-1.");
        a.setQuestion(q);
        a.setOp(op);
        return a;
    }

}
