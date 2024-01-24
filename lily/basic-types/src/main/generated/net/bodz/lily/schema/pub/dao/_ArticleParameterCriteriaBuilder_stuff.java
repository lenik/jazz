package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ArticleParameterCriteriaBuilder_stuff<self_t extends _ArticleParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final LongField articleId = _long("article");

    public final IntegerField parameterId = integer("parm");

    public final IntegerField ival = integer("ival");

    public final DoubleField fval = _double("fval");

    public final StringField sval = string("sval");

}
