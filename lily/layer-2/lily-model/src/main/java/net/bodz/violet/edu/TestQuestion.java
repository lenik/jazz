package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

/**
 * 测试题目
 */
@Table(name = "testq")
@IdType(Long.class)
public class TestQuestion
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Course course;
    int insertPos;
    String answer;

    public TestQuestion() {
    }

    /**
     * 所属课程
     */
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * 答案的文本插入点（对应填空题等）
     */
    public int getInsertPos() {
        return insertPos;
    }

    public void setInsertPos(int insertPos) {
        this.insertPos = insertPos;
    }

    /**
     * 参考答案
     *
     * 适用于填空、问答题等。 对于选择题，此answer忽略。
     */
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
