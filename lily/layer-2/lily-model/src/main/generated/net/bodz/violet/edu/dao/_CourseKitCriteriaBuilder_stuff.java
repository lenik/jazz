package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _CourseKitCriteriaBuilder_stuff<self_t extends _CourseKitCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField courseId = integer("course");

    public final IntegerField favCount = integer("nfav");

    public final IntegerField voteCount = integer("nvote");

    public final IntegerField hateCount = integer("nhate");


}
