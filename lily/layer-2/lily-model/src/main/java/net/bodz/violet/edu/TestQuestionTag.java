package net.bodz.violet.edu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoTag;

@IdType(Integer.class)
public class TestQuestionTag
        extends CoTag <TestQuestionTag>{

    private static final long serialVersionUID = 1L;

    public TestQuestionTag() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
