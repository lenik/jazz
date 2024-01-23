package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoCodeCriteriaBuilder;

public class _CourseTagCriteriaBuilder_stuff<self_t extends _CourseTagCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
