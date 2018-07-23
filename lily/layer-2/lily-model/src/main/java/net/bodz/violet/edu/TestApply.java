package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.contact.Person;

/**
 * 测试交卷
 */
@Table(name = "testapply")
@IdType(Long.class)
public class TestApply
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    TestPaper paper;
    Person person;
    double score;

    public TestApply() {
    }

    /**
     * 试卷模板
     */
    public TestPaper getPaper() {
        return paper;
    }

    public void setPaper(TestPaper paper) {
        this.paper = paper;
    }

    /**
     * 考生
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 计算得分
     */
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
