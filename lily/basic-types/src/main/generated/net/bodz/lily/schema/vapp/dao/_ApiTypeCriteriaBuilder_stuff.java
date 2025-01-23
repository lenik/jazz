package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.vapp.ApiType;

@ForEntityType(ApiType.class)
public class _ApiTypeCriteriaBuilder_stuff<self_t extends _ApiTypeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final StringField uom = string("uom");

}
