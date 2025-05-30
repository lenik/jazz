package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabFn;

@ForEntityType(FabFn.class)
public class _FabFnCriteriaBuilder_stuff<self_t extends _FabFnCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nref");

}
