package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.vapp.VApp;

@ForEntityType(VApp.class)
public class _VAppCriteriaBuilder_stuff<self_t extends _VAppCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField code = string("code");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField reqId = integer("req");

    public final IntegerField categoryId = integer("cat");

    public final StringField secret = string("secret");

}
