package net.bodz.lily.schema.geo.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.geo.Zone;

@ForEntityType(Zone.class)
public class _ZoneCriteriaBuilder_stuff<self_t extends _ZoneCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final StringField country = string("country");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final StringField telCode = string("telcode");

    public final StringField postCode = string("postcode");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final DiscreteField<JsonVariant> data = discrete("\"data\"", JsonVariant.class);

}
