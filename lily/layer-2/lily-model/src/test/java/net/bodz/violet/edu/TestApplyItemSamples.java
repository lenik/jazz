package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class TestApplyItemSamples
        extends TestSamples {

    public static TestApplyItem build(TestApply apply, TestQuestion question) {
        TestApplyItem a = new TestApplyItem();
        a.setLabel("testApplyItem-1");
        a.setDescription("A testApplyItem named testApplyItem-1.");
        a.setApply(apply);
        a.setQuestion(question);
        return a;
    }

}
