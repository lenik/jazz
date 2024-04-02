package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestQuestionTag;

@ForEntityType(TestQuestionTag.class)
public class _TestQuestionTagCriteriaBuilder_stuff<self_t extends _TestQuestionTagCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
