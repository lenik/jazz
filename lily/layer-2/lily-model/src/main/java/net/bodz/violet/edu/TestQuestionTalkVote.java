package net.bodz.violet.edu;

import net.bodz.lily.template.CoTalk;

public class TestQuestionTalkVote
        extends CoTalk<TestQuestionTalk> {

    private static final long serialVersionUID = 1L;

    TestQuestionTalk talk;

    public TestQuestionTalkVote() {
    }

    public TestQuestionTalk getTalk() {
        return talk;
    }

    public void setTalk(TestQuestionTalk talk) {
        this.talk = talk;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
