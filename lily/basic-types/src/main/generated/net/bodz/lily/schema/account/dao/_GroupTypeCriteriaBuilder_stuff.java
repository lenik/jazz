package net.bodz.lily.schema.account.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.account.GroupType;

@ForEntityType(GroupType.class)
public class _GroupTypeCriteriaBuilder_stuff<self_t extends _GroupTypeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** Group type name (unique) */
    public final StringField name = string("\"name\"");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField dummy = integer("dummy");

}
