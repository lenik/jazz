package net.bodz.lily.pub.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PostParameterCriteriaBuilder_stuff<self_t extends _PostParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField postId = _long("post");

    public final IntegerField parameterId = integer("parm");

    public final IntegerField ival = integer("ival");

    public final DoubleField fval = _double("fval");

    public final StringField sval = string("sval");

}
