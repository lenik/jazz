package net.bodz.lily.schema.util.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _UomCriteriaBuilder_stuff<self_t extends _UomCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final StringField prop = string("prop");

    public final IntegerField stdId = integer("std");

    public final DoubleField scale = _double("\"scale\"");

}
