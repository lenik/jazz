package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "testq_vote")
public class TestQuestionVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    TestQuestion testQuestion;

    public TestQuestionVote() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
