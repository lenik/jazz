package net.bodz.violet.edu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Integer.class)
public class TestQuestionFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    TestQuestion question;

    public TestQuestionFav() {
    }

    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }

}
