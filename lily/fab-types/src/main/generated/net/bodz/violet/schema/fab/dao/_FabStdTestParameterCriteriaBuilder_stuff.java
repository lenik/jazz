package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FabStdTestParameterCriteriaBuilder_stuff<self_t extends _FabStdTestParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField testId = integer("test");

    public final BooleanField required = bool("required");

    public final StringField expected = string("expected");

}
