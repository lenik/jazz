package net.bodz.lily.schema.util.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.util.UomRow;

@ForEntityType(UomRow.class)
public class _UomRowCriteriaBuilder_stuff<self_t extends _UomRowCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final StringField property = string("prop");

    public final IntegerField standardId = integer("std");

    public final DoubleField scale = _double("\"scale\"");

}
