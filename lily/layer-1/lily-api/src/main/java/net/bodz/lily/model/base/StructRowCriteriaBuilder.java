package net.bodz.lily.model.base;

import org.joda.time.DateTime;

import net.bodz.lily.criteria.CriteriaBuilder;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class StructRowCriteriaBuilder<self_t extends StructRowCriteriaBuilder<self_t>>
        extends CriteriaBuilder<self_t> {

    public final DateField<DateTime> creation = date("creation", DateTime.class);
    public final DateField<DateTime> lastmod = date("lastmod", DateTime.class);

    public final IntegerField version = integer("version");

}
