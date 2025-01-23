package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.vapp.VApi;

@ForEntityType(VApi.class)
public class _VApiCriteriaBuilder_stuff<self_t extends _VApiCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField appId = integer("app");

    public final IntegerField apiId = integer("api");

    public final StringField callback = string("callback");

}
