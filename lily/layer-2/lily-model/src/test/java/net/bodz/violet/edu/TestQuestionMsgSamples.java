package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class TestQuestionMsgSamples
        extends TestSamples {

    public static TestQuestionMsg build(TestQuestion q, User op) {
        TestQuestionMsg a = new TestQuestionMsg();
        a.setSubject("testQuestionMsg-1");
        a.setText("A testQuestionMsg named testQuestionMsg-1.");
        a.setQuestion(q);
        a.setOp(op);
        return a;
    }

}
