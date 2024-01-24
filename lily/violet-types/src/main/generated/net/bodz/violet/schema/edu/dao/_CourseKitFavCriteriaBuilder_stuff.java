package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _CourseKitFavCriteriaBuilder_stuff<self_t extends _CourseKitFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField coursekitId = integer("coursekit");

    public final IntegerField userId = integer("\"user\"");

}
