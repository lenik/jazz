package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabStdProcessCriteriaBuilder_stuff<self_t extends _FabStdProcessCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final BooleanField valid = bool("\"valid\"");

    public final IntegerField outputId = integer("\"output\"");

    public final IntegerField functionId = integer("fn");

    public final IntegerField duration = integer("duration");

    public final BooleanField strict = bool("strict");

    public final IntegerField testId = integer("test");

}
