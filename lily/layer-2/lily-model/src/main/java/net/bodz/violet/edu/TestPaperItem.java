package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 考卷项目
 */
@Table(name = "testpaperl")
@IdType(Integer.class)
public class TestPaperItem
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    TestPaper paper;
    TestQuestion question;
    double score;

    public TestPaperItem() {
    }

    public TestPaper getPaper() {
        return paper;
    }

    public void setPaper(TestPaper paper) {
        this.paper = paper;
    }

    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
