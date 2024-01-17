package net.bodz.violet.edu.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.edu.TestQuestion;

/**
* @label TestQuestion
*/
@ObjectType(TestQuestion.class)
public class TestQuestionIndex
        extends CoIndex<TestQuestion, TestQuestionCriteriaBuilder> {

    public TestQuestionIndex() {
    }

}
