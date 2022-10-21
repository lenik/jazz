package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

/**
 * 候选答案
 */
@Table(name = "testans")
@IdType(Long.class)
public class TestAnswer
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    TestQuestion question;
    boolean valid;

    public TestAnswer() {
    }

    /**
     * 对应问题
     */
    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }

    /**
     * 是否正确答案
     */
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
