package net.bodz.lily.schema.geo.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.geo.ZoneCategory;

@ForEntityType(ZoneCategory.class)
public class _ZoneCategoryCriteriaBuilder_stuff<self_t extends _ZoneCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField refCount = integer("nref");

}
