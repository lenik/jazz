package net.bodz.lily.schema.meta.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.CategoryDef;

@ForEntityType(CategoryDef.class)
public class _CategoryDefCriteriaBuilder_stuff<self_t extends _CategoryDefCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField refCount = integer("nobj");

}
